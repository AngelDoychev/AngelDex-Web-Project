package com.example.angeldex.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "currencies")
public class Currency extends BaseEntity{
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @ManyToOne
    private UserEntity user;

    public Currency() {
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
