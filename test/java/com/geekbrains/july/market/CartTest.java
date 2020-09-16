package com.geekbrains.july.market;


import com.geekbrains.july.market.beans.Cart;
import com.geekbrains.july.market.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class CartTest {
    @Autowired
    private Cart cart;

    @Test
    public void cartFillingTest() {
        for (int i = 0; i < 10; i++) {
            long productId = i / 2 + 1;
            Product product = new Product(productId, "Product #" + productId, new BigDecimal(100 + productId * 10));
            cart.add(product);
        }
        Assertions.assertEquals(5, cart.getItems().size());
        cart.clear();
        Assertions.assertEquals(0, cart.getItems().size());
    }
}