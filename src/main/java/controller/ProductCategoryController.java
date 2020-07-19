package controller;

import persist.ProductCategory;
import persist.ProductCategoryRepository;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class ProductCategoryController implements Serializable {

    @EJB
    ProductCategoryRepository productCategoryRepository;

    private ProductCategory productCategory;

    private List<ProductCategory> productCategories;

    public void preloadProductCategories(ComponentSystemEvent componentSystemEvent) {
        this.productCategories = productCategoryRepository.findAll();
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String createProductCategory() {
        this.productCategory = new ProductCategory();
        return "/product_category.xhtml";
    }

    public List<ProductCategory> getAllProductCategories() {
        return productCategories;
    }

    public String editProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
        return "/product_category.xhtml";
    }
    public void deleteProductCategory(ProductCategory productCategory) {
        productCategoryRepository.delete(productCategory.getId());
    }

    public String saveProductCategory() {
        if (productCategory.getId() == null){
            productCategoryRepository.insert(productCategory);
        } else {
            productCategoryRepository.update(productCategory);
        }
        return "/product_categories.xhtml";
    }
}
