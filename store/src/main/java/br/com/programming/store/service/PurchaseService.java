package br.com.programming.store.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.programming.store.client.HaulerClient;
import br.com.programming.store.client.ProviderClient;
import br.com.programming.store.dto.DeliveryInfoDTO;
import br.com.programming.store.dto.OrderInfoDTO;
import br.com.programming.store.dto.ProviderInfoDTO;
import br.com.programming.store.dto.PurchaseDTO;
import br.com.programming.store.dto.VoucherInfoDTO;
import br.com.programming.store.model.Purchase;
import br.com.programming.store.model.PurchaseState;
import br.com.programming.store.repository.PurchaseRepository;

@Service
public class PurchaseService {

    @Autowired
    private ProviderClient providerClient;

    @Autowired
    private HaulerClient haulerClient;

    @Autowired
    private PurchaseRepository purchaseRepository;

    private void savePurchaseChanges(Purchase p) {
        purchaseRepository.save(p);
    }

    private Purchase createPurchase(PurchaseDTO purchase) {
        Purchase p = new Purchase();
        p.setState(PurchaseState.RECEIVED);
        p.setFromAddress(purchase.getAddress().toString());
        savePurchaseChanges(p);
        return p;
    }

    private OrderInfoDTO registryOrder(Purchase p, PurchaseDTO purchaseDTO) {
        OrderInfoDTO order = providerClient.sendOrder(purchaseDTO.getItems());
        p.setState(PurchaseState.ORDER_EXECUTED);
        p.setOrderId(order.getId());
        p.setEstimatedTime(order.getEstimatedTime());
        savePurchaseChanges(p);
        return order;
    }

    private DeliveryInfoDTO createDeliveryInfo(OrderInfoDTO order, PurchaseDTO purchaseDTO) {
        ProviderInfoDTO stateInfo = providerClient.getInfoByState(purchaseDTO.getAddress().getState());
        DeliveryInfoDTO deliveryInfo = new DeliveryInfoDTO();
        deliveryInfo.setDeliveryDate(LocalDate.now().plusDays(order.getEstimatedTime()));
        deliveryInfo.setProviderAddress(stateInfo.getAddress());
        deliveryInfo.setFinalAddress(purchaseDTO.getAddress().toString());
        deliveryInfo.setOrderId(order.getId());
        return deliveryInfo;
    }

    private void createVoucher(Purchase p, DeliveryInfoDTO deliveryInfo) {
        VoucherInfoDTO voucherInfo = haulerClient.scheduleDelivery(deliveryInfo);
        p.setState(PurchaseState.SCHEDULED_DELIVERY);
        p.setVoucherId(voucherInfo.getNumber());
        p.setProviderAddress(deliveryInfo.getProviderAddress());
        savePurchaseChanges(p);
    }

    @HystrixCommand(threadPoolKey = "getByIdThreadPool")
    public Purchase getById(Long id) {
        return purchaseRepository.findById(id).orElse(new Purchase());
    }

    @HystrixCommand(fallbackMethod = "purchaseFallback", threadPoolKey = "purchaseThreadPool")
    public Purchase purchase(PurchaseDTO purchaseDTO) {
        Purchase p = createPurchase(purchaseDTO);
        purchaseDTO.setPurchaseId(p.getId());
        OrderInfoDTO order = registryOrder(p, purchaseDTO);
        DeliveryInfoDTO deliveryInfoDTO = createDeliveryInfo(order, purchaseDTO);
        createVoucher(p, deliveryInfoDTO);
        return p;
    }

    public Purchase purchaseFallback(PurchaseDTO purchaseDTO) {
        if (purchaseDTO.getPurchaseId() != null) {
            return purchaseRepository.findById(purchaseDTO.getPurchaseId()).get();
        }
        Purchase purchaseFallback = new Purchase();
        purchaseFallback.setFromAddress(purchaseDTO.getAddress().toString());
        return purchaseFallback;
    }
}
