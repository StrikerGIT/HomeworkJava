package controller;


import persist.Product;
import persist.ProductCategory;
import persist.ProductCategoryRepository;
import persist.ProductRepository;
import service.ProductRepr;
import service.ProductServiceLocal;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@SessionScoped
@Named
public class ProductController implements Serializable {

    @EJB
    private ProductServiceLocal productService;

    @EJB
    private ProductCategoryRepository productCategoryRepository;

    private ProductRepr product;

    private List<ProductRepr> products;

    public ProductRepr getProduct() {
        return product;
    }

    public void setProduct(ProductRepr product) {
        this.product = product;
    }

    public String createProduct() {
        this.product = new ProductRepr();
        return "/product.xhtml";
    }

    public List<ProductRepr> getAllProducts() {
        return products;
    }

    public String editProduct(ProductRepr product) {
        this.product = product;
        return "/product.xhtml";
    }
    public void deleteProduct(ProductRepr product) {
        productService.delete(product.getId());
    }

    public String saveProduct() {
        if (product.getId() == null){
            productService.insert(product);
        } else {
            productService.update(product);
        }
        return "/index.xhtml";
    }

    public void preloadProducts(ComponentSystemEvent cse){
        this.products = productService.findAll();
    }

    public List<ProductCategory> getAllCategories() {
        return productCategoryRepository.findAll();
    }
}
