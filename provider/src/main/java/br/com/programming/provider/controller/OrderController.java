package br.com.programming.provider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.programming.provider.dto.OrderItemDTO;
import br.com.programming.provider.model.Order;
import br.com.programming.provider.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Order sentOrder(@RequestBody List<OrderItemDTO> orderItem) {
        return orderService.sendOrder(orderItem);
    }
}
