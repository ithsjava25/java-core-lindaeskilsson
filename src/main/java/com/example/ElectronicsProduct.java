package com.example;

import java.math.BigDecimal;
import java.util.UUID;

// Subklass som representerar elektronikprodukter
// Ärver från Product och implementerar interface Shippable
public class ElectronicsProduct extends Product implements Shippable {
    // Garantitid i månader
    private final int warrantyMonths;
    // Vikt i kilogram (lagras som BigDecimal)
    private final BigDecimal weight;

    // Konstruktor med BigDecimal för vikt
    public ElectronicsProduct(UUID uuid, String name, Category category,
                              BigDecimal price, int warrantyMonths, BigDecimal weight) {
        super(uuid, name, category, price);

        // garanti får inte vara negativ
        if (warrantyMonths < 0)
            throw new IllegalArgumentException("Warranty months cannot be negative.");

        //  vikt måste vara större än noll
        if (weight.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Weight must be greater than zero.");

        this.warrantyMonths = warrantyMonths;
        this.weight = weight;
    }

    // Extra konstruktor för tester som skickar in double istället för BigDecimal
    public ElectronicsProduct(UUID uuid, String name, Category category,
                              BigDecimal price, int warrantyMonths, double weight) {
        // Konverterar double till BigDecimal och skickar vidare till huvudkonstruktorn
        this(uuid, name, category, price, warrantyMonths, BigDecimal.valueOf(weight));
    }

    // overridear metode från Shippable
    // Returnerar vikten som double (matchar interfacet)
    @Override
    public double weight() {
        return weight.doubleValue();
    }

    // Metod som beräknar fraktkostnad:
    @Override
    public BigDecimal calculateShippingCost() {
        BigDecimal base = BigDecimal.valueOf(79);
        if (weight.compareTo(BigDecimal.valueOf(5.0)) > 0) {
            base = base.add(BigDecimal.valueOf(49));
        }
        return base;
    }

    // Returnerar produktens detaljer i textform
    @Override
    public String productDetails() {
        return "Electronics: " + name() + ", Warranty: " + warrantyMonths + " months";
    }
}