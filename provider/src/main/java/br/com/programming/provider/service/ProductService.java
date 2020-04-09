package br.com.programming.provider.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.programming.provider.model.Product;
import br.com.programming.provider.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductByState(String state) {
        return productRepository.findByState(state);
    }
}
