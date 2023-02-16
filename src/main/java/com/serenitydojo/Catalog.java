package com.serenitydojo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Catalog {
    private int applePrice = 0;
    private int orangePrice = 0;
    private List<Fruit> availableFruits = new ArrayList<>();

    public int getPricePerKiloGrams(Fruit fruit) {
        if (!listAvailableFruits().contains(fruit)) {
            throw new FruitUnavailableException();
        }

        if (fruit instanceof Apple) {
            return applePrice;
        }
        if (fruit instanceof Orange) {
            return orangePrice;
        }
        throw new UnsupportedOperationException();
    }

    public void setFruitePrice(Fruit fruit, int i) {

        if (i <= 0) {
            throw new UnsupportedOperationException();
        }

        if (fruit instanceof Apple) {
            applePrice = i;
            return;
        }
        if (fruit instanceof Orange) {
            orangePrice = i;
            return;
        }
        throw new UnsupportedOperationException();
    }

    public void setAvailableFruits(int defaultPrice, Fruit... fruits) {
        for (Fruit fruit : fruits) {
            setAvailable(fruit);
            setFruitePrice(fruit, defaultPrice);
        }
    }

    private void setAvailable(Fruit fruit) {
        availableFruits.add(fruit);
    }

    public List<Fruit> listAvailableFruits() {
        return availableFruits.stream().sorted(Comparator.comparing(Fruit::getName)).collect(Collectors.toList());
    }

    public static class FruitUnavailableException extends RuntimeException {
    }
}
