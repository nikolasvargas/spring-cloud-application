package br.com.programming.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.programming.store.client.ProviderClient;
import br.com.programming.store.controller.dto.OrderInfoDTO;
import br.com.programming.store.controller.dto.PurchaseDTO;
import br.com.programming.store.model.Purchase;
import br.com.programming.store.repository.PurchaseRepository;

@Service
public class PurchaseService {

    @Autowired
    private ProviderClient providerClient;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @HystrixCommand(threadPoolKey = "getByIdThreadPool")
    public Purchase getById(Long id) {
        return purchaseRepository.findById(id).orElse(new Purchase());
    }

    @HystrixCommand(fallbackMethod = "purchaseFallback", threadPoolKey = "purchaseThreadPool")
    public Purchase purchase(PurchaseDTO purchase) {
        OrderInfoDTO order = providerClient.purchaseItem(purchase.getItems());
        Purchase p = new Purchase(order.getId(), order.getEstimatedTime(), purchase.getAddress().toString());
        purchaseRepository.save(p);
        return p;
    }

    public Purchase purchaseFallback(PurchaseDTO purchase) {
        Purchase purchaseFallback = new Purchase();
        purchaseFallback.setFromAddress(purchase.getAddress().toString());
        return purchaseFallback;
    }
}
