package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public final class Warehouse {
    // Singleton-hantering per namn
    private static final Map<String, Warehouse> INSTANCES = new ConcurrentHashMap<>();

    private final String name;
    private final List<Product> products = new ArrayList<>();

    public String getName() {
        return name;
    }

    // Privat konstruktor
    private Warehouse(String name) {
        this.name = name;
    }

    // Singleton per unikt namn
    public static Warehouse getInstance(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Warehouse name cannot be null or empty.");
        }
        return INSTANCES.computeIfAbsent(name.trim(), Warehouse::new);
    }

    // Överlagrad metod för tester som anropar getInstance() utan argument
    public static Warehouse getInstance() {
        return getInstance("default");
    }

    // Metod som tar bort alla produkter
    public void clearProducts() {
        products.clear();
    }

    // Metod som kollar om lagret är tomt
    public boolean isEmpty() {
        return products.isEmpty();
    }

    // Metod som lägger till produkt
    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        // Kontrollera om produkten redan finns (dubblett-ID)
        if (products.stream().anyMatch(p -> p.uuid().equals(product.uuid()))) {
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }
        products.add(product);
    }

    // Tar bort en produkt baserat på produktens ID
    public void remove(UUID id) {
        products.removeIf(p -> p.uuid().equals(id));
    }

    // Hämtar en produkt på UUID
    public Optional<Product> getProductById(UUID id) {
        return products.stream().filter(p -> p.uuid().equals(id)).findFirst();
    }

    // Grupperar produkter per kategori
    public Map<Category, List<Product>> getProductsGroupedByCategories() {
        return products.stream().collect(Collectors.groupingBy(Product::category));
    }

    // Returnerar alla produkter som en omodifierbar lista
    public List<Product> getProducts() {
        return Collections.unmodifiableList(new ArrayList<>(products));
    }

    // Returnerar alla produkter som har gått ut (interface: Perishable)
    public List<Perishable> expiredProducts() {
        LocalDate today = LocalDate.now();
        return products.stream()
                .filter(p -> p instanceof Perishable per && per.expirationDate().isBefore(today))
                .map(p -> (Perishable) p)
                .toList();
    }

    // Returnerar alla produkter som kan skickas (interface: Shippable)
    public List<Shippable> shippableProducts() {
        return products.stream()
                .filter(p -> p instanceof Shippable)
                .map(p -> (Shippable) p)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // Uppdaterar priset på en produkt och spårar ändringen
    public void updateProductPrice(UUID id, BigDecimal newPrice) {
        Product product = getProductById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
        product.price(newPrice); // använder settern i Product
    }
}
