package br.com.programming.provider.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.programming.provider.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

    List<Product> findByState(String state);

    List<Product> findByIdIn(List<Long> ids);
}
