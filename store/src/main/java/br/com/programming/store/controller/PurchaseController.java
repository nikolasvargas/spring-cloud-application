package br.com.programming.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.programming.store.dto.PurchaseDTO;
import br.com.programming.store.model.Purchase;
import br.com.programming.store.service.PurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @RequestMapping("/{id}")
    public Purchase getPurchaseById(@PathVariable("id") Long id) {
        return purchaseService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Purchase purchase(@RequestBody PurchaseDTO purchase) {
        return purchaseService.purchase(purchase);
    }

}
