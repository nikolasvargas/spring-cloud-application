package br.com.programming.provider.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.programming.provider.model.ProviderInfo;
import br.com.programming.provider.repository.InfoRepository;

@Service
public class InfoService {

    @Autowired
    private InfoRepository infoRepository;

    public List<ProviderInfo> getInfoByState(String state) {
        return infoRepository.findByState(state);
    }
}
