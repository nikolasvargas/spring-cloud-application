package br.com.programming.hauler.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.programming.hauler.dto.DeliveryDTO;
import br.com.programming.hauler.dto.VoucherDTO;
import br.com.programming.hauler.service.DeliveryService;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private DeliveryService deliveryService;

    public VoucherDTO schedule(@RequestBody DeliveryDTO deliveryDTO) {
        return deliveryService.scheduleDelivery(deliveryDTO);
    }
}
