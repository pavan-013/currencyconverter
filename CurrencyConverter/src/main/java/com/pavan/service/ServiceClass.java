package com.pavan.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pavan.model.CurrencyConversionRequest;
import com.pavan.model.CurrencyConversionResponse;

@Service
public class ServiceClass {

	private final RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl = "https://api.exchangerate-api.com/v4/latest/";
    @Value("${exchange.api.key}")
    private String apiKey;

    public Map<String, Object> getExchangeRates(String base) {
        try {
        	String url = apiUrl + base + "?apikey=" + apiKey;
            return restTemplate.getForObject(url, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("External API unavailable.");
        }
    }

    public CurrencyConversionResponse convertCurrency(CurrencyConversionRequest request) {
        Map<String, Object> rates = getExchangeRates(request.getFrom());
        Map<String, Double> rateMap = (Map<String, Double>) rates.get("rates");
        if (!rateMap.containsKey(request.getTo())) {
            throw new IllegalArgumentException("Invalid currency code.");
        }
        double rate = rateMap.get(request.getTo());
        double convertedAmount = request.getAmount() * rate;
        return new CurrencyConversionResponse(request.getFrom(), request.getTo(), request.getAmount(), convertedAmount);
    }
}
