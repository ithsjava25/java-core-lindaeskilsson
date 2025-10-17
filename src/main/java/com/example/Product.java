package com.example;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

// här har vi skapat en superklass som sedan subklasserna FoodProduct och ElectronicsProdukt ska
// bygga utifrån.
    public abstract class Product {

        //  egenskaper.
        private final UUID id;             // ett unikt ID för varje produkt
        private final String name;         // produktens namn
        private final Category category;   // produktens kategori (t.ex. "Dryck", "Mat", "Kläder")
        private BigDecimal price;          // produktens pris (kan ändras)

        //  Konstruktor
        public Product(String name, Category category, BigDecimal price) {
            this.id = UUID.randomUUID(); // genererar automatiskt ett unikt ID
            this.name = Objects.requireNonNull(name, "Name cannot be null");
            this.category = Objects.requireNonNull(category, "Category cannot be null");
            this.price = Objects.requireNonNull(price, "Price cannot be null");
        }

        //  Getters
        public UUID id() {
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

        //  Setter
        public void price(BigDecimal newPrice) {
            if (newPrice == null || newPrice.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Price must be non-null and non-negative");
            }
            this.price = newPrice;
        }

        // Abstrakt metod (ska implementeras i subklasser)
        public abstract String productDetails();

        //  Hjälpmetod för utskrift
        @Override
        public String toString() {
            return "%s (%s) - %s kr".formatted(name, category, price);
        }
    }

