package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.service.ProductService;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/product")
@Controller
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String productList(Model model,
                           @RequestParam(name = "minCoast", required = false) Integer minCoast,
                           @RequestParam(name = "maxCoast", required = false) Integer maxCoast,
                           @RequestParam(name = "productTitle", required = false) String productTitle,
                           @RequestParam(name = "page") Optional<Integer> page,
                           @RequestParam(name = "size") Optional<Integer> size) {
        logger.info("Product list. With minCoast = {} and maxCoast = {}", minCoast, maxCoast);

        Page<Product> productPage = productService.filterByCoast(minCoast, maxCoast, productTitle,
                PageRequest.of(page.orElse(1) - 1, size.orElse(5)));

        model.addAttribute("productPage", productPage);
        model.addAttribute("prevPageNumber", productPage.hasPrevious() ? productPage.previousPageable().getPageNumber() + 1 : -1);
        model.addAttribute("nextPageNumber", productPage.hasNext() ? productPage.nextPageable().getPageNumber() + 1 : -1);
        model.addAttribute("minCoast", minCoast);
        model.addAttribute("maxCoast", maxCoast);

        return "products";
    }

    @GetMapping("new")
    public String createProduct(Model model) {
        logger.info("Create product form");

        model.addAttribute("product", new Product());
        return "product";
    }

    @GetMapping("edit")
    public String editProduct(@RequestParam ("id") Long id, Model model) {
        logger.info("Edit product with id {}", id);

        model.addAttribute("product", productService.findById(id));
        return "product";
    }

    @PostMapping
    public String saveProduct(@Valid Product product, BindingResult bindingResult) {
        logger.info("Save product method");

        if(bindingResult.hasErrors()){
            return "product";
        }
        productService.save(product);
        return "redirect:/product";
    }

    @DeleteMapping
    public String deleteProduct(@RequestParam ("id") Long id){
        logger.info("Delete product with Id = {}", id);

        productService.delete(id);
        return "redirect:/product";
    }
}
