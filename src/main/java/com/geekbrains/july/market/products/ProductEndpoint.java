package com.geekbrains.july.market.products;

import com.geekbrains.july.market.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.com/spring/ws/products";

    private ProductsService productService;

    @Autowired
    public ProductEndpoint(ProductsService productService) {
        this.productService = productService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProduct(@RequestPayload GetProductsRequest request) {
        GetProductsResponse response = new GetProductsResponse();
        response.setProduct(productService.getAllProductDTO());
        return response;
    }
}
