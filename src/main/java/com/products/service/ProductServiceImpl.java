package com.products.service;

import com.products.entity.Product;
import com.products.entity.ProductInput;
import com.products.exception.NotFoundException;
import com.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product getProductById(final UUID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Product not found. ID: %s", id)));
    }

    @Override
    public Product saveProduct(ProductInput productInput) {
        var productExists = repository.findByName(productInput.name());

        if (productExists.isEmpty()) {
            var timeNow = LocalDateTime.now();
            return repository.save(Product.builder()
                    .id(null)
                    .name(productInput.name())
                    .description(productInput.description())
                    .price(productInput.price())
                    .department(productInput.department())
                    .isAvailable(productInput.isAvailable())
                    .createdAt(timeNow)
                    .updatedAt(timeNow)
                    .build());
        } else {
            return productExists.get();
        }
    }

    @Override
    public Product updateProduct(final UUID id, ProductInput productInput) {
        var current = getProductById(id);

        return repository.save(new Product(
                current.getId(),
                productInput.name() != null ? productInput.name() : current.getName(),
                productInput.description() != null ? productInput.description() : current.getDescription(),
                productInput.price() != null ? productInput.price() : current.getPrice(),
                productInput.department() != null ? productInput.department() : current.getDepartment(),
                productInput.isAvailable() != null ? productInput.isAvailable() : current.getIsAvailable(),
                current.getCreatedAt(),
                LocalDateTime.now()
        ));
    }

    @Override
    public Boolean deleteProduct(final UUID id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
