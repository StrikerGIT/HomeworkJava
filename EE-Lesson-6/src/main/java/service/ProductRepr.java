package service;

import persist.Product;
import persist.ProductCategory;

import javax.persistence.*;
import java.io.Serializable;

public class ProductRepr implements Serializable {

    private Long id;

    private String title;

    private Integer coast;

    private Long categoryId;

    private String categoryName;

    public ProductRepr() {
    }

    public ProductRepr(Long id, String title, Integer coast, Long categoryId, String categoryName) {
        this.id = id;
        this.title = title;
        this.coast = coast;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public ProductRepr(Product product) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCoast() {
        return coast;
    }

    public void setCoast(Integer coast) {
        this.coast = coast;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
