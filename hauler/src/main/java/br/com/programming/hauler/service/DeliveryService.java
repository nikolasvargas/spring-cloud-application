package br.com.programming.hauler.service;

import org.springframework.stereotype.Service;

import br.com.programming.hauler.dto.DeliveryDTO;
import br.com.programming.hauler.dto.VoucherDTO;
import br.com.programming.hauler.model.Delivery;
import br.com.programming.hauler.repository.DeliveryRepository;

@Service
public class DeliveryService {

    private DeliveryRepository repository;

    private Delivery createDelivery(DeliveryDTO deliveryDTO) {
        Delivery delivery = new Delivery();
        delivery.setCollectionDate(deliveryDTO.getDeliveryDate());
        delivery.setExpectedDelivery(deliveryDTO.getDeliveryDate().plusDays(1l));
        delivery.setProviderAddress(deliveryDTO.getProviderAddress());
        delivery.setFinalAddress(deliveryDTO.getFinalAddress());
        delivery.setOrderId(deliveryDTO.getOrderId());
        repository.save(delivery);

        return delivery;
    }

    private VoucherDTO createVoucher(Delivery order) {
        VoucherDTO voucher = new VoucherDTO();
        voucher.setNumber(order.getId());
        voucher.setForecastDate(order.getExpectedDelivery());
        return voucher;
    }

    public VoucherDTO scheduleDelivery(DeliveryDTO deliveryDTO) {
        Delivery item = createDelivery(deliveryDTO);
        VoucherDTO voucher = createVoucher(item);
        return voucher;
    }
}
