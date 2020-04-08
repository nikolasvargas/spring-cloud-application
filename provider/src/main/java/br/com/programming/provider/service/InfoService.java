package br.com.programming.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.programming.provider.model.ProviderInfo;

@Service
public class InfoService {

    @Autowired
    private InfoRepository infoRepository;

    public ProviderInfo getInfoByState(String state) {
        return infoRepository.findByState(state);
    }
}
