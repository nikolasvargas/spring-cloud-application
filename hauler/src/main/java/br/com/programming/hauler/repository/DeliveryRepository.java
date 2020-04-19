package br.com.programming.hauler.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.programming.hauler.model.Delivery;

public interface DeliveryRepository extends CrudRepository<Delivery, Long>{

}
