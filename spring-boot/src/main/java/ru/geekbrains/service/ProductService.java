package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.repo.ProductRepository;
import ru.geekbrains.persist.repo.ProductSpecification;

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

    public Page<Product> filterByCoast(Integer minCoast, Integer maxCoast, String productTitle, Pageable pegeable) {
        Specification<Product> specification = ProductSpecification.trueLiteral();
        if (minCoast != null){
            specification = specification.and(ProductSpecification.coastGreaterThanOrEqual(minCoast));
        }
        if (maxCoast != null){
            specification = specification.and(ProductSpecification.coastLessThanOrEqual(maxCoast));
        }
        if (productTitle != null && !productTitle.isEmpty()){
            specification = specification.and(ProductSpecification.findByProductTitle(productTitle));
        }

        return repository.findAll(specification, pegeable);
    }

    @Transactional
    public Product save(Product product) {
        repository.save(product);
        return product;
    }

    @Transactional(readOnly = true)
    public Optional<Product> findById(long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
