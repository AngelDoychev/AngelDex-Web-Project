package com.example.angeldex.model.dtos;

import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public class CurrencyDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private BigDecimal price;

    public CurrencyDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
