package ru.geekbrains.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.dto.MyException;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.service.ProductService;

import java.util.List;

@RequestMapping("/api/v1/product")
@RestController
public class ProductResource {

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping(path ="/{id}/id")
    public Product findById(@PathVariable("id") long id) {
        return productService.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @ResponseBody
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
            if (product.getId() != null){
                throw new IllegalArgumentException("Id is already in use");
            }
        return productService.save(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping(path ="/{id}/id")
    public void deleteProduct(@PathVariable("id") long id) {
        productService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<MyException> notFoundExceptionHandler(NotFoundException exc) {
        MyException productsErrorResponse = new MyException("not found",HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(productsErrorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<MyException> IllegalArgumentException(IllegalArgumentException exception) {
        MyException productsErrorResponse = new MyException(exception.getLocalizedMessage(),HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(productsErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
