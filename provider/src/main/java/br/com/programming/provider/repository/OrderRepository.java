package br.com.programming.provider.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.programming.provider.model.PurchaseOrder;

public interface OrderRepository extends CrudRepository<PurchaseOrder, Long>{

}
