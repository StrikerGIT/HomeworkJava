package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
                           @RequestParam(name = "page", required = false) Optional<Integer> page,
                           @RequestParam(name = "size", required = false) Optional<Integer> size) {
        logger.info("Product list. With minCoast = {} and maxCoast = {}", minCoast, maxCoast);

        Page<Product> productPage = productService.filterByCoast(minCoast, maxCoast,
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

    @PostMapping
    public String saveProduct(@Valid Product product, BindingResult bindingResult) {
        logger.info("Save product method");

        if(bindingResult.hasErrors()){
            return "product";
        }
        productService.save(product);
        return "redirect:/product";
    }
}
