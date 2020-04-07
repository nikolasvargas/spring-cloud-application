package br.com.programming.store.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.programming.store.controller.dto.PurchaseDTO;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

	@RequestMapping(method = RequestMethod.POST)
	public void purchase(@RequestBody PurchaseDTO purchase) {

	}

}
