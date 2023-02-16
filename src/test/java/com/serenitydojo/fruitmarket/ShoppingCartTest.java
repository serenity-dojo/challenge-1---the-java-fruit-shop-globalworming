package com.serenitydojo.fruitmarket;

import com.serenitydojo.Apple;
import com.serenitydojo.Catalog;
import com.serenitydojo.Orange;
import com.serenitydojo.ShoppingCart;
import org.junit.Test;

public class ShoppingCartTest {

    @Test
    public void whereShoppingCartKeepsRunnningTotal() {
        // given
        Apple apple = new Apple();
        var catalog = CatalogTest.catalogWithAvailable(apple);
        var cart = new ShoppingCart(catalog);
        double initialTotal = cart.getTotal();
        assert 0 == initialTotal;

        // when
        cart.addItem(apple, 1);

        // then
        assert cart.getTotal() > initialTotal;
    }

    @Test
    public void whereShoppingCartKeepsRunnningTotal2() {
        // given
        Apple apple = new Apple();
        var catalog = CatalogTest.catalogWithAvailable(apple);
        var cart = new ShoppingCart(catalog);
        double initialTotal = cart.getTotal();
        assert 0 == initialTotal;

        // when
        cart.addItem(apple, 1);
        cart.addItem(apple, 1);

        // then
        assert cart.getTotal() == 2;
    }


}
