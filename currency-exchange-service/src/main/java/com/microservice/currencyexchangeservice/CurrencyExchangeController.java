package com.microservice.currencyexchangeservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
@Slf4j
@RestController
public class CurrencyExchangeController {
    @Autowired
    private Environment environment;
    @Autowired
    private CurrencyExchangeRepo currencyExchangeRepo;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to
    ){
        CurrencyExchange currencyExchange =currencyExchangeRepo.findByFromAndTo(from,to);
        if(currencyExchange ==null){
            throw new RuntimeException("Unable to find data");
        }
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        log.info("{} to {}",from,to);
        return currencyExchange;
    }
}
