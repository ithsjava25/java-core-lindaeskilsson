package com.example;


import java.math.BigDecimal;
import java.time.LocalDate;
// subklass för matprodukter som extendar product. Lägger till utgångsdatum med hjälp av localdate.

public class FoodProduct extends Product {
    private final LocalDate bestBefore;

    public FoodProduct(String name, LocalDate bestBefore, Category category, double price, LocalDate bestBefore1)
    {
        super(name, category, BigDecimal.valueOf(price));
        this.bestBefore = bestBefore;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    @Override
    public String productDetails() {
        return "";
    }
}
