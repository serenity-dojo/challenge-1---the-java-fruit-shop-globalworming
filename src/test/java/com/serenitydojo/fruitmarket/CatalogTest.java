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
        var apple = new Apple();
        var catalog = catalogWithAvailable(apple);
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
        var orange = new Orange();
        Catalog catalog = catalogWithAvailable(orange);
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
    public void whereWeGetThePriceOfSomethingUnavailable() {
        boolean exceptionCaught = false;

        // when
        try {
            currentPriceOfApple(new Catalog());
        } catch (Catalog.FruitUnavailableException e) {
            exceptionCaught = true;
        }
        //then
        assert exceptionCaught;
    }

    public static Catalog catalogWithAvailable(Fruit fruit) {
        var catalog = new Catalog();
        catalog.setAvailableFruits(1, fruit);
        return catalog;
    }

    @Test
    public void whereFruitsAreAvailable() {
        // given
        var catalog = catalogWithAvailable(new Apple());
        // when
        List<Fruit> fruits = catalog.listAvailableFruits();
        // then
        assert fruits.get(0) instanceof Apple;
        int size = fruits.size();
        assert size == 1;
    }

    @Test
    public void whereMoreFruitsAreAvailable() {
        // given
        Catalog catalog = catalogWithTwoFruits();
        // when
        List<Fruit> fruits = catalog.listAvailableFruits();
        // then
        int size = fruits.size();
        assert size == 2;
    }

    private static Catalog catalogWithTwoFruits() {
        var catalog = new Catalog();
        catalog.setAvailableFruits(1, new Apple(), new Orange());
        return catalog;
    }

    @Test
    public void whereAvailableFruitAreListedAlphabetically() {
        // given
        var catalog = catalogWithTwoFruits();
        // when
        List<Fruit> fruits = catalog.listAvailableFruits();
        //then
        listIsInAlphabeticalOrder(fruits);
    }



    private static void listIsInAlphabeticalOrder(List<Fruit> fruits) {
        assert fruits.get(0).getName().equals("Apple");
        assert fruits.get(1).getName().equals("Orange");
    }


    private int currentPriceOfOrange(Catalog catalog, Orange orange) {
        return catalog.getPricePerKiloGrams(orange);
    }

    private static int currentPriceOfApple(Catalog catalog) {
        return catalog.getPricePerKiloGrams(new Apple());
    }

    private void updateCatalog(Catalog catalog, Fruit fruit, int i) {
        catalog.setFruitePrice(fruit, i);
    }
}

