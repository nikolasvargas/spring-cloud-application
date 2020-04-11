package br.com.programming.provider.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.programming.provider.dto.PurchaseItemDTO;
import br.com.programming.provider.model.PurchaseOrder;
import br.com.programming.provider.model.PurchaseItem;
import br.com.programming.provider.model.Product;
import br.com.programming.provider.repository.OrderRepository;
import br.com.programming.provider.repository.ProductRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    private List<Long> toProductList(List<PurchaseItemDTO> items) {
        return items.stream().map(i -> i.getId()).collect(Collectors.toList());
    }

    private PurchaseOrder createOrder(List<PurchaseItemDTO> orderItem) {
        PurchaseOrder order = new PurchaseOrder(toOrderItem(orderItem));
        order.setEstimatedTime(orderItem.size());
        return order;
    }

    private PurchaseItem createOrderItem(Product product, PurchaseItemDTO item) {
        PurchaseItem orderItem = new PurchaseItem();
        orderItem.setProduct(product);
        orderItem.setAmount(item.getAmount());
        return orderItem;
    }

    private List<PurchaseItem> toOrderItem(List<PurchaseItemDTO> items) {
        List<Product> productsList = productRepository.findByIdIn(toProductList(items));

        return items
                .stream()
                .map(item -> { Product product = productsList
                    .stream()
                    .filter(p -> p.getId() == item.getId())
                    .findFirst()
                    .get();
                    return createOrderItem(product, item);
                })
                .collect(Collectors.toList());
    }

    public PurchaseOrder sendOrder(List<PurchaseItemDTO> orderItem) {
        if (orderItem == null) {
            return null;
        }

        PurchaseOrder order = createOrder(orderItem);
        return orderRepository.save(order);
    }

    public PurchaseOrder getOrderById(Long id) {
        return this.orderRepository.findById(id).orElse(new PurchaseOrder());
    }
}
