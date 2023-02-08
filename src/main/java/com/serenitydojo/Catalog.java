package com.serenitydojo;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private int applePrice = 0;
    private int orangePrice = 0;
    private List<Fruit> availableFruits = new ArrayList<>();

    public int getFruitPrice(Fruit fruit) {
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

    public void setAvailableFruits(Fruit... fruits) {
        for (Fruit fruit : fruits) {
            setAvailable(fruit);
        }

    }

    private void setAvailable(Fruit fruit) {
        availableFruits.add(fruit);
    }

    public List<Fruit> list() {
        return availableFruits;
    }
}
