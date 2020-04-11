package br.com.programming.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.programming.store.client.ProviderClient;
import br.com.programming.store.controller.dto.OrderInfoDTO;
import br.com.programming.store.controller.dto.ProviderInfoDTO;
import br.com.programming.store.controller.dto.PurchaseDTO;
import br.com.programming.store.model.Purchase;

@Service
public class PurchaseService {

    @Autowired
    private ProviderClient providerClient;

    public Purchase purchase(PurchaseDTO purchase) {
        ProviderInfoDTO info = providerClient.getInfoByState(purchase.getAddress().getState());

        OrderInfoDTO order = providerClient.purchaseItem(purchase.getItems());

        Purchase p = new Purchase(order.getId(), order.getEstimatedTime(), purchase.getAddress().toString());

        return p;
    }
}
