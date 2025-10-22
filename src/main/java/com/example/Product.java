package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class Product {
    private final UUID id;            // unikt ID för varje produkt
    private final String name;        // produktens namn
    private final Category category;  // produktens kategori
    private BigDecimal price;         // produktens pris (kan ändras)

    // Konstruktor
    public Product(UUID id, String name, Category category, BigDecimal price) {
        if (id == null || name == null || category == null || price == null) {
            throw new IllegalArgumentException("None of the fields can be null.");
        }
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    // Getters
    public UUID uuid() {
        return id;
    }

    public String name() {
        return name;
    }

    public Category category() {
        return category;
    }

    public BigDecimal price() {
        return price;
    }

    // Setter
    public void price(BigDecimal newPrice) {
        if (newPrice == null || newPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative or null.");
        }
        this.price = newPrice;
    }

    // Abstrakt metod, tvingar subklasser att beskriva sina detaljer
    public abstract String productDetails();
}