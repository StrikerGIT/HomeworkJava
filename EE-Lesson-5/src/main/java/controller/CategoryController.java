package controller;

import persist.Category;
import persist.CategoryRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class CategoryController implements Serializable {

    @Inject
    CategoryRepository categoryRepository;

    private Category category;

    private List<Category> categories;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String createCategory() {
        this.category = new Category();
        return "/category.xhtml";
    }

    public List<Category> getAllCategories() {
        return categories;
    }

    public String editCategory(Category category) {
        this.category = category;
        return "/category.xhtml";
    }
    public void deleteCategory(Category category) {
        categoryRepository.delete(category.getId());
    }

    public String saveCategory() {
        if (category.getId() == null){
            categoryRepository.insert(category);
        } else {
            categoryRepository.update(category);
        }
        return "/categories.xhtml";
        //?faces-redirect=true
    }

    public void preloadCategories(ComponentSystemEvent cse){
        this.categories = categoryRepository.findAll();
    }
}
