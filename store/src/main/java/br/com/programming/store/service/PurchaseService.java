package br.com.programming.store.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.programming.store.controller.dto.ProviderInfoDTO;
import br.com.programming.store.controller.dto.PurchaseDTO;

@Service
public class PurchaseService {

    public void purchase(PurchaseDTO purchase) {
        RestTemplate client = new RestTemplate();
        ResponseEntity<ProviderInfoDTO> exchange = client.exchange(
                "http://provider/info/" + purchase.getAddress().getState(),
                HttpMethod.GET, null,
                ProviderInfoDTO.class);
        System.out.println(exchange.getBody().getAddress());
    }
}
