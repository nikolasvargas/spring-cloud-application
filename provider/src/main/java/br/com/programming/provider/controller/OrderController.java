package br.com.programming.provider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.programming.provider.dto.PurchaseItemDTO;
import br.com.programming.provider.model.PurchaseOrder;
import br.com.programming.provider.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.POST)
    public PurchaseOrder sendOrder(@RequestBody List<PurchaseItemDTO> orderItem) {
        return orderService.sendOrder(orderItem);
    }

    @RequestMapping("/{id}")
    public PurchaseOrder getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }
}
