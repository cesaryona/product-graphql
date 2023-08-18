package com.products.controller;

import com.products.entity.Product;
import com.products.entity.ProductInput;
import com.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @QueryMapping
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @QueryMapping
    public Product getProductById(@Argument final UUID id) {
        return productService.getProductById(id);
    }

    @MutationMapping
    public Product saveProduct(@Argument ProductInput productInput) {
        return productService.saveProduct(productInput);
    }

    @MutationMapping
    public Product updateProduct(@Argument final UUID id, @Argument ProductInput productInput) {
        return productService.updateProduct(id, productInput);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument final UUID id) {
        return productService.deleteProduct(id);
    }
}
