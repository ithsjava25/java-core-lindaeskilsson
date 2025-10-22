package com.example;

import java.math.BigDecimal;

public interface Shippable {
    //vikt och kostnad
    BigDecimal calculateShippingCost();
    double weight();
}