package br.com.programming.store.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.programming.store.model.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Long>{

}
