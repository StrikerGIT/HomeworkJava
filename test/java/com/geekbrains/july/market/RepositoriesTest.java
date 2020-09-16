package com.geekbrains.july.market;

import com.geekbrains.july.market.entities.Product;
import com.geekbrains.july.market.repositories.ProductsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoriesTest {
    @Autowired
    private ProductsRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void productRepositoryTest() {
        Product product = new Product(null, "Super product", new BigDecimal(10000));
        Product out = entityManager.persist(product);
        entityManager.flush();

        List<Product> productsList = productRepository.findAll();

        Assertions.assertEquals(5, productsList.size());
        Assertions.assertEquals("Super product", productsList.get(4).getTitle());
    }

    @Test
    public void initDbTest() {
        List<Product> productsList = productRepository.findAll();
        Assertions.assertEquals(4, productsList.size());
    }
}
