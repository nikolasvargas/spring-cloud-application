package br.com.programming.provider.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.programming.provider.model.ProviderInfo;

@Repository
public interface InfoRepository extends CrudRepository<ProviderInfo, Long>{

	ProviderInfo findByState(String state);
}
