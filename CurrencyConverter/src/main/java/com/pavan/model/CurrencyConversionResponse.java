package com.pavan.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConversionResponse {

	private String from;
    private String to;
    private double amount;
    private double convertedAmount;
}
