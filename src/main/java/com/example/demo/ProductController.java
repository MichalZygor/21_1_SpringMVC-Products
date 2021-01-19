package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", ProductCategory.values());
        return "home";
    }


    @GetMapping("/list")
    public String categories(Model model, @RequestParam(required = false, name = "productCategory") ProductCategory activeCategory) {
        model.addAttribute("categories", ProductCategory.values());
        model.addAttribute("selectedCategory", activeCategory);

        if (activeCategory == ProductCategory.ALL_PRODUCTS) {
            model.addAttribute("products", productRepository.findAll());
            model.addAttribute("productsTotalSum", productRepository.sumAll());
        } else {
            model.addAttribute("products", productRepository.filterByCategories(activeCategory));
            model.addAttribute("productsTotalSum", productRepository.sumByCategory(activeCategory));
        }
        return "category";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("products", productRepository.getProducts());
        model.addAttribute("categories", ProductCategory.findWithoutAll());
        return "add";
    }

    @PostMapping("/add")
    public String addProduct(Product product) {
        productRepository.add(product);
        return "redirect:/list?productCategory=" + ProductCategory.ALL_PRODUCTS;
    }
}
