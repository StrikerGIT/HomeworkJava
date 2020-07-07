package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persist.Product;
import persist.ProductCategory;
import persist.ProductCategoryRepository;
import persist.ProductRepository;
import rest.ProductServiceRs;
import soapService.ProductServiceWs;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@WebService(endpointInterface = "soapService.ProductServiceWs", serviceName = "ProductService")
public class ProductService implements ProductServiceLocal, ProductServiceWs, ProductServiceRs {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ProductCategoryRepository productCategoryRepository;

    @Override
    @TransactionAttribute
    public void insert(ProductRepr productRepr){
        ProductCategory category = productCategoryRepository.findById(productRepr.getCategoryId());
        productRepository.insert(new Product(null, productRepr.getTitle(), productRepr.getCoast(), category));
    }

    @Override
    @TransactionAttribute
    public void update(ProductRepr productRepr){
        ProductCategory category = productCategoryRepository.findById(productRepr.getCategoryId());
        productRepository.update(new Product(productRepr.getId(), productRepr.getTitle(), productRepr.getCoast(), category));
    }

    @Override
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

    @Override
    public List<ProductRepr> findAllWs() {
        return productRepository.findAllProductRepr();
    }

    @Override
    public ProductRepr findByIdWs(long id) {
        return productRepository.findProductReprById(id);
    }

}
