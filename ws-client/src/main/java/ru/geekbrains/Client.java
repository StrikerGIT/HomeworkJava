package ru.geekbrains;

import persist.Product;
import service.ProductRepr;
import soapService.ProductServiceWs;

import java.net.MalformedURLException;
import java.net.URL;

public class Client {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/e-shop/ProductService/ProductService?wsdl");
        ProductService productService = new ProductService(url);

        ProductServiceWs productServicePort = productService.getProductServicePort();

        productServicePort.findAllWs().forEach(product -> System.out.println(product.getTitle()));

        ProductRepr newProduct = new ProductRepr();

        productServicePort.insert(newProduct);

        productServicePort.update(newProduct);

        productServicePort.delete(newProduct.getId());
        }
}
