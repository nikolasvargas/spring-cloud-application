package br.com.programming.provider.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.programming.provider.model.ProviderInfo;

@Repository
public interface InfoRepository extends CrudRepository<ProviderInfo, Long>{

    List<ProviderInfo> findByState(String state);
}
