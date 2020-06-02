package ru.geekbrains.persist.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.entity.User;


import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    Product findByTitle(String title);

    Page<Product> findByCoastGreaterThanEqual(Integer minCoast, Pageable pegeable);

    Page<Product> findByCoastLessThanEqual(Integer maxCoast, Pageable pegeable);

    Page<Product> findByCoastBetween(Integer minCoast, Integer maxCoast, Pageable pegeable);
}
