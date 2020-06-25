package controller;


import persist.Product;
import persist.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@SessionScoped
@Named
public class ProductController implements Serializable {

    @Inject
    ProductRepository productRepository;

    private Product product;

    private List<Product> products;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String createProduct() {
        this.product = new Product();
        return "/product.xhtml";
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public String editProduct(Product product) {
        this.product = product;
        return "/product.xhtml";
    }
    public void deleteProduct(Product product) {
        productRepository.delete(product.getId());
    }

    public String saveProduct() {
        if (product.getId() == null){
            productRepository.insert(product);
        } else {
            productRepository.update(product);
        }
        return "/index.xhtml";
        //?faces-redirect=true
    }

    public void preloadProducts(ComponentSystemEvent cse){
        this.products = productRepository.findAll();
    }
}
