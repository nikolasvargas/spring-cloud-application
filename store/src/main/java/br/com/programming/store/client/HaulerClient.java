package br.com.programming.store.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.programming.store.dto.DeliveryInfoDTO;
import br.com.programming.store.dto.VoucherInfoDTO;

@FeignClient("hauler")
public interface HaulerClient {

    @RequestMapping(method = RequestMethod.POST, value = "/delivery")
    VoucherInfoDTO scheduleDelivery(DeliveryInfoDTO order);
}
