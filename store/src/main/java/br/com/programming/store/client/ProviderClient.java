package br.com.programming.store.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.programming.store.dto.OrderInfoDTO;
import br.com.programming.store.dto.ProviderInfoDTO;
import br.com.programming.store.dto.PurchaseItemDTO;


@FeignClient("provider")
public interface ProviderClient {

    @RequestMapping("/info/{state}")
    ProviderInfoDTO getInfoByState(@PathVariable String state);

    @RequestMapping(method = RequestMethod.POST, value = "/order")
    OrderInfoDTO purchaseItem(List<PurchaseItemDTO> items);
}
