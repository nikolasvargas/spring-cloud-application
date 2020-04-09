package br.com.programming.provider.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.programming.provider.dto.OrderItemDTO;
import br.com.programming.provider.model.Order;
import br.com.programming.provider.model.OrderItem;
import br.com.programming.provider.model.Product;
import br.com.programming.provider.repository.OrderRepository;
import br.com.programming.provider.repository.ProductRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    private List<Product> toProductList(List<OrderItemDTO> items) {
        List<Long> itemIds = items
                .stream()
                .map(i -> i.getId())
                .collect(Collectors.toList());

        return productRepository.findByIdIn(itemIds);
    }

    private Order createOrder(List<OrderItem> orderItem) {
        Order order = new Order();
        order.setEstimatedTime(orderItem.size());
        return order;
    }

    private OrderItem createOrderItem(Product product, OrderItemDTO item) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setAmount(item.getQuantidade());
        return orderItem;
    }

    private List<OrderItem> toOrderItem(List<OrderItemDTO> items) {
        List<Product> productsList = toProductList(items);

        return items.stream().map(item -> {
            Product product = productsList
                    .stream()
                    .filter(p -> p.getId() == item.getId())
                    .findFirst()
                    .get();
            return createOrderItem(product, item);
        }).collect(Collectors.toList());

    }

    public Order sendOrder(List<OrderItemDTO> orderItem) {
        if (orderItem == null) {
            return null;
        }

        Order order = createOrder(toOrderItem(orderItem));
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return this.orderRepository.findById(id).orElse(new Order());
    }
}
