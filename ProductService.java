package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.repo.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return repository.findAll();
    }

    public Page<Product> filterByCoast(Integer minCoast, Integer maxCoast, Pageable pegeable) {
        if (minCoast == null & maxCoast == null){
            return repository.findAll(pegeable);
        }
        if (minCoast != null & maxCoast == null){
            return repository.findByCoastGreaterThanEqual(minCoast, pegeable);
        }
        if (minCoast == null & maxCoast != null){
            return repository.findByCoastLessThanEqual(maxCoast, pegeable);
        }
        return repository.findByCoastBetween(minCoast, maxCoast, pegeable);
    }

    @Transactional
    public void save(Product product) {
        repository.save(product);
    }

    @Transactional(readOnly = true)
    public Optional<Product> findById(long id) {
        return repository.findById(id);
    }
}
