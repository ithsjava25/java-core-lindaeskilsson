package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FoodProduct extends Product implements Perishable, Shippable {
    private final LocalDate expirationDate;
    private final double weight;

    // Konstruktor som tar BigDecimal och double
    public FoodProduct(UUID uuid, String name, Category category,
                       BigDecimal price, LocalDate expirationDate, double weight) {
        super(uuid, name, category, price);
        if (price.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Price cannot be negative.");
        if (weight < 0)
            throw new IllegalArgumentException("Weight cannot be negative.");
        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    // Extra konstruktor som tar double istället för BigDecimal
    // (så tester som skickar in double inte kraschar)
    public FoodProduct(UUID uuid, String name, Category category,
                       BigDecimal price, LocalDate expirationDate, BigDecimal weight) {
        this(uuid, name, category, price, expirationDate, weight.doubleValue());
    }


    @Override
    public LocalDate expirationDate() {
        return expirationDate;
    }

    @Override
    public double weight() {
        return weight;
    }

    @Override
    public BigDecimal calculateShippingCost() {
        // shipping cost = vikt * 50
        return BigDecimal.valueOf(weight * 50);
    }

    @Override
    public String productDetails() {
        return "Food: " + name() + ", Expires: " + expirationDate;
    }
}
