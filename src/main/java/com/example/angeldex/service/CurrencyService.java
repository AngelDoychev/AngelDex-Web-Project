package com.example.angeldex.service;

import com.example.angeldex.model.entities.Currency;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    Optional<Currency> findCurrencyById(long id);
    Currency updateCurrency(Currency currency);
    Currency createCurrency(String name, BigDecimal price);
    void deleteCurrencyByID(long id);
    List<Currency> findAllCurrencies();
}
