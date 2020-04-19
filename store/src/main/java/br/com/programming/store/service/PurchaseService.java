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
import br.com.programming.store.repository.PurchaseRepository;

@Service
public class PurchaseService {

    @Autowired
    private ProviderClient providerClient;

    @Autowired
    private HaulerClient haulerClient;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @HystrixCommand(threadPoolKey = "getByIdThreadPool")
    public Purchase getById(Long id) {
        return purchaseRepository.findById(id).orElse(new Purchase());
    }

    @HystrixCommand(fallbackMethod = "purchaseFallback", threadPoolKey = "purchaseThreadPool")
    public Purchase purchase(PurchaseDTO purchase) {

        ProviderInfoDTO stateInfo = providerClient.getInfoByState(purchase.getAddress().getState());

        OrderInfoDTO order = providerClient.purchaseItem(purchase.getItems());

        DeliveryInfoDTO deliveryInfo = new DeliveryInfoDTO();
        deliveryInfo.setOrderId(order.getId());
        deliveryInfo.setDeliveryDate(LocalDate.now().plusDays(order.getEstimatedTime()));
        deliveryInfo.setProviderAddress(stateInfo.getAddress());
        deliveryInfo.setFinalAddress(purchase.getAddress().toString());

        VoucherInfoDTO voucherInfo = haulerClient.scheduleDelivery(deliveryInfo);

        Purchase p = new Purchase(
                order.getId(),
                voucherInfo.getNumber(),
                order.getEstimatedTime(),
                purchase.getAddress().toString(),
                deliveryInfo.getProviderAddress(),
                deliveryInfo.getFinalAddress());

        purchaseRepository.save(p);

        return p;
    }

    public Purchase purchaseFallback(PurchaseDTO purchase) {
        Purchase purchaseFallback = new Purchase();
        purchaseFallback.setFromAddress(purchase.getAddress().toString());
        return purchaseFallback;
    }
}
