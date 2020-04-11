package br.com.programming.provider.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.programming.provider.model.ProviderInfo;
import br.com.programming.provider.service.InfoService;

@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @RequestMapping("/{state}")
    public Optional<ProviderInfo> getInfoByState(@PathVariable String state) {
        return Optional.ofNullable(
                infoService
                    .getInfoByState(state)
                    .stream()
                    .findFirst()
                    .get()
                );
    }
}
