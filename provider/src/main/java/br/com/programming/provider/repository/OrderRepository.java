package br.com.programming.provider.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.programming.provider.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
