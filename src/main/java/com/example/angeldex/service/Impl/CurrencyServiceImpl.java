package com.example.angeldex.service.Impl;

import com.example.angeldex.model.entities.Currency;
import com.example.angeldex.repository.CurrencyRepository;
import com.example.angeldex.service.CurrencyService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Optional<Currency> findCurrencyById(long id) {
        return this.currencyRepository.findById(id);
    }

    @Override
    public Currency updateCurrency(Currency currency) {
        return this.currencyRepository.save(currency);
    }

    @Override
    public Currency createCurrency(String name, BigDecimal price) {
        Currency currency = new Currency();
        currency.setName(name);
        currency.setPrice(price);
        return this.currencyRepository.save(currency);
    }

    @Override
    public void deleteCurrencyByID(long id) {
        this.currencyRepository.deleteById(id);
    }

    @Override
    public List<Currency> findAllCurrencies() {
        return this.currencyRepository.findAll();
    }
}
