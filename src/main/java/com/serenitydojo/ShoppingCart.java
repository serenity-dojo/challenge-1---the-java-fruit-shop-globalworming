package com.serenitydojo;


import java.util.*;

public class ShoppingCart {

    Map<Fruit, Integer> fruitToKiloGrams = new HashMap<>();
    private final Catalog catalog;

    public ShoppingCart(Catalog catalog) {
        this.catalog = catalog;
    }

    public double getTotal() {
        Set<Fruit> fruits = fruitToKiloGrams.keySet();
        return fruits.stream().mapToDouble(catalog::getPricePerKiloGrams).sum();
    }

    public void addItem(Fruit fruit, int kilograms) {
        fruitToKiloGrams.put(fruit, kilograms);
    }
}
