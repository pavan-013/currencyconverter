package com.pavan;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.pavan.model.CurrencyConversionRequest;
import com.pavan.model.CurrencyConversionResponse;
import com.pavan.service.ServiceClass;

@SpringBootTest
class CurrencyServiceTest {

	@MockBean
    private ServiceClass currencyService;

    @Test
    void testConvertCurrency() {
        CurrencyConversionRequest request = new CurrencyConversionRequest();
        Mockito.when(currencyService.convertCurrency(request)).thenReturn(new CurrencyConversionResponse("USD", "EUR", 100, 94.5));
        CurrencyConversionResponse response = currencyService.convertCurrency(request);
        assertEquals(94.5, response.getConvertedAmount());
    }

}
