package com.serenitydojo.fruitmarket;

import com.serenitydojo.*;
import org.junit.Test;

import java.util.List;

public class CatalogTest {

    @Test
    public void whenThereIsAShop() {
        var shop = new Shop();
        assert shop.getCatalog() != null;
    }

    @Test
    public void whereFruitPriceIsUpdated() {
        // given
        var catalog = new Catalog();
        Apple apple = new Apple();
        var startValue = currentPriceOfApple(catalog);
        //when
        int newPrice = startValue + 1;
        updateCatalog(catalog, apple, newPrice);
        //then
        assert currentPriceOfApple(catalog) == newPrice;
        assert currentPriceOfApple(catalog) != startValue;
    }

    @Test
    /**
     * prevent setting negative price because ...
     */
    public void whereFruitPriceIsMadeNegative() {
        // given
        boolean exceptionCaught = false;
        var catalog = new Catalog();
        var orange = new Orange();
        var startValue = currentPriceOfOrange(catalog, orange);
        //when
        try {
            int newPrice = -1;
            updateCatalog(catalog, orange, newPrice);
        } catch (RuntimeException e) {
            exceptionCaught = true;
        }
        //then
        assert exceptionCaught;
        assert currentPriceOfOrange(catalog, orange) == startValue;
    }

    @Test
    public void whereFruitsAreAvailable() {
        // given
        var catalog = new Catalog();
        catalog.setAvailableFruits(new Apple());
        // when
        List<Fruit> fruits = catalog.list();
        // then
        assert fruits.get(0) instanceof Apple;
        int size = fruits.size();
        assert size == 1;
    }

    @Test
    public void whereMoreFruitsAreAvailable() {
        // given
        var catalog = new Catalog();
        catalog.setAvailableFruits(new Apple(), new Orange());
        // when
        List<Fruit> fruits = catalog.list();
        // then
        int size = fruits.size();
        assert size == 2;
    }

    private int currentPriceOfOrange(Catalog catalog, Orange orange) {
        return catalog.getFruitPrice(orange);
    }

    private static int currentPriceOfApple(Catalog catalog) {
        return catalog.getFruitPrice(new Apple());
    }

    private void updateCatalog(Catalog catalog, Fruit fruit, int i) {
        catalog.setFruitePrice(fruit, i);
    }
}

