package com.pavan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.model.CurrencyConversionRequest;
import com.pavan.service.ServiceClass;

@RestController
@RequestMapping("/currency-converting")
public class ControllerClass {

	private final ServiceClass currencyService;

    public ControllerClass(ServiceClass currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/rates")
    public ResponseEntity<?> getExchangeRates(@RequestParam(defaultValue = "USD") String base) {
        try {
            return ResponseEntity.ok(currencyService.getExchangeRates(base));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Error fetching exchange rates.");
        }
    }

    @PostMapping("/convert")
    public ResponseEntity<?> convertCurrency(@RequestBody CurrencyConversionRequest request) {
        try {
            return ResponseEntity.ok(currencyService.convertCurrency(request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid currency code provided.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Error converting currency.");
        }
    }
}
