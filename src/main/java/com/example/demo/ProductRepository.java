package com.example.demo;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

@Repository
public class ProductRepository {
    private ArrayList<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product("Czekoladki", 22.25, "src/czekoladki.png", ProductCategory.GROCERIES));
        products.add(new Product("Chleb", 4.85, "src/chleb.png", ProductCategory.GROCERIES));
        products.add(new Product("Herbatka owocowa teekannne blueberry 20x2,25gr", 3.55, "https://selgros24.pl/images/prodImages/TEEKANNE_Herbatka_owocowa_TEEKANNE_Blueberry_20_x_2_25g_32384968_0_350_350.jpg", ProductCategory.GROCERIES));
        products.add(new Product("Talerz 22cm", 5.20, "src/talerz.png", ProductCategory.HOUSEHOLD_ITEMS));
        products.add(new Product("Czajnik retro Amica KF4042", 158.67, "https://selgros24.pl/images/prodImages/Amica_Czajnik_Retro_Amica_KF4042_39402367_0_350_350.jpg", ProductCategory.HOUSEHOLD_ITEMS));
        products.add(new Product("Młotek", 22.25, "src/mlotek.png", ProductCategory.OTHER));
        products.add(new Product("Projektor Overmax multipic 3.5", 478.47, "https://selgros24.pl/images/prodImages/Overmax_Projektor_Overmax_Multipic_3_5_63183925_1_350_350.jpg", ProductCategory.OTHER));
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Product> findAll() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public BigDecimal sumAll() {
        BigDecimal sum = BigDecimal.valueOf(products.stream()
                .mapToDouble(product -> product.getPrice())
                .sum());
        return sum.setScale(2, RoundingMode.HALF_UP);
    }

    public ArrayList<Product> filterByCategories(ProductCategory category) {
        ArrayList<Product> filtered = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory() == category) {
                filtered.add(product);
            }
        }
        return filtered;
    }

    public BigDecimal sumByCategory(ProductCategory category) {
        BigDecimal sum = BigDecimal.valueOf(products.stream()
                .filter(product -> product.getCategory() == category)
                .mapToDouble(product -> product.getPrice())
                .sum());
        return sum.setScale(2, RoundingMode.HALF_UP);
    }

    public void add(Product product) {
        products.add(product);
    }
}
