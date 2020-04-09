package br.com.programming.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.programming.store.client.ProviderClient;
import br.com.programming.store.controller.dto.ProviderInfoDTO;
import br.com.programming.store.controller.dto.PurchaseDTO;

@Service
public class PurchaseService {

    @Autowired
    private ProviderClient providerClient;

    public void purchase(PurchaseDTO purchase) {
        ProviderInfoDTO info = providerClient.getInfoByState(purchase.getAddress().getState());
        System.out.println(info.getAddress());
    }
}
