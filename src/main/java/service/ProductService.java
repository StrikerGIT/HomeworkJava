package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persist.Product;
import persist.ProductCategory;
import persist.ProductCategoryRepository;
import persist.ProductRepository;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;

@Stateless
public class ProductService implements ProductServiceLocal {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ProductCategoryRepository productCategoryRepository;

    @Override
    @RolesAllowed("MANAGER")
    @TransactionAttribute
    public void insert(ProductRepr productRepr){
        ProductCategory category = productCategoryRepository.findById(productRepr.getCategoryId());
        productRepository.insert(new Product(null, productRepr.getTitle(), productRepr.getCoast(), category));
    }

    @Override
    @RolesAllowed("MANAGER")
    @TransactionAttribute
    public void update(ProductRepr productRepr){
        ProductCategory category = productCategoryRepository.findById(productRepr.getCategoryId());
        productRepository.update(new Product(productRepr.getId(), productRepr.getTitle(), productRepr.getCoast(), category));
    }

    @Override
    @RolesAllowed("MANAGER")
    @TransactionAttribute
    public void delete(long id) {
        productRepository.delete(id);
    }

    @Override
    public ProductRepr findById(long id) {
        return productRepository.findProductReprById(id);
    }

    @Override
    public List<ProductRepr> findAll() {
        return productRepository.findAllProductRepr();
    }

}
