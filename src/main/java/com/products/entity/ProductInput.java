package com.products.entity;

import java.math.BigDecimal;

public record ProductInput(String name, String description, BigDecimal price, String department, Boolean isAvailable) {
}
