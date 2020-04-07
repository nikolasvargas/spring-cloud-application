package br.com.programming.store.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.programming.store.controller.dto.ProviderInfoDTO;
import br.com.programming.store.controller.dto.PurchaseDTO;

public class PurchaseService {
	
	public void purchase(PurchaseDTO purchase) {
		RestTemplate client = new RestTemplate();
		ResponseEntity<ProviderInfoDTO> exchange = client.exchange(
				"http://localhost:8081/info/"+purchase.getAddress().getState(),
				HttpMethod.GET,
				null,
				ProviderInfoDTO.class
		);
		System.out.println(exchange.getBody().getAddress());
	}
}
