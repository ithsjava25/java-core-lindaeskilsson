package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Category {
    private final String name;
    private static final Map<String, Category> CACHE = new HashMap<>();

    // Privat konstruktor – kan bara anropas inifrån klassen
    private Category(String name) {
        this.name = name;
    }

    // metod för validering, trimma och göra första bokstaven stor, resten små.
    // Cacha instanser.
    // retunera samma instans för samma namn.
    public static Category of(String name) {

        if (name == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }
        String trimmed = name.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Category name can't be blank");
        }

        String normalizedName = trimmed.substring(0, 1).toUpperCase() +
                trimmed.substring(1).toLowerCase();

        Category cached = CACHE.get(normalizedName);
        if (cached == null) {
            cached = new Category(normalizedName);
            CACHE.put(normalizedName, cached);
        }

        return cached;
    }

    // Getter testerna kalla på name()
    public String name() {
        return name;
    }

    // För  utskrift
    @Override
    public String toString() {
        return name;
    }

    // Två kategorier med samma namn ska vara lika
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

