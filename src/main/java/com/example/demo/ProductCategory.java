package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

enum ProductCategory {
    ALL_PRODUCTS("Wszystkie produkty"),
    GROCERIES("Artykuły spożywcze"),
    HOUSEHOLD_ITEMS("Artykuły gospodarstwa domowego"),
    OTHER("Inne");

    private final String description;

    ProductCategory(String description) {
        this.description = description;
    }

    public static List<ProductCategory> findWithoutAll() {
        List<ProductCategory> filtered;
        filtered = Arrays.stream(ProductCategory.values())
                .filter(category -> !(category == ProductCategory.ALL_PRODUCTS))
                .collect(Collectors.toList());
        return filtered;
    }

    public String getDescription() {
        return description;
    }
}
