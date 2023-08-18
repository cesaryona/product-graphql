package com.products.service;

import com.products.entity.Product;
import com.products.entity.ProductInput;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> getAll();

    Product getProductById(final UUID id);

    Product saveProduct(ProductInput productInput);

    Product updateProduct(final UUID id, ProductInput productInput);

    Boolean deleteProduct(final UUID id);
}
