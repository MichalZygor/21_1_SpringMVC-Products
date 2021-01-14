package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private List<Products> products;
    private List<Categories> categories;

    public MainController(){
        products = new ArrayList<>();
        products.add(new Products(1, "Czekoladki", 22.25, "src/czekoladki.png","Inne"));
        products.add(new Products(2, "Chleb", 4.85, "src/chleb.png","Artykuły spożywcze"));
        products.add(new Products(3, "Talerz 22cm", 5.20, "src/talerz.png","Artykuły gospodarstwa domowego</"));
        products.add(new Products(4, "Młotek", 22.25, "src/mlotek.png","Inne"));

        categories = new ArrayList<>();
        categories.add(new Categories(1, "Wszystkie kategorie", true));
        categories.add(new Categories(2, "Artykuły spożywcze", false));
        categories.add(new Categories(3, "Artykuły gospodarstwa domowego", false));
        categories.add(new Categories(4, "Inne", false));
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categories);
        return "home";
    }


    @GetMapping("/list")
    public String categories(Model model, @RequestParam(name = "category") String selectedCategory){

        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory", selectedCategory);

        if(selectedCategory.equals("Wszystkie kategorie")){
            model.addAttribute("products", products);
            model.addAttribute("productsTotalSum", products.stream()
                                                                .mapToDouble(product -> product.getPrice())
                                                                .sum());
        }
        else {
            model.addAttribute("products", products.stream()
                                                        .filter(product -> product.getCategory().equals(selectedCategory))
                                                        .collect(Collectors.toList()));
            model.addAttribute("productsTotalSum", products.stream()
                                                                .filter(product -> product.getCategory().equals(selectedCategory))
                                                                .mapToDouble(product -> product.getPrice())
                                                                .sum());
        }
        return "category";
    }

    @GetMapping("/add")
    public String add(Model model) {
        //Products products  = new Products();
        model.addAttribute("newProduct", new Products());
        model.addAttribute("products", products);
        model.addAttribute("categories", categories.stream()
                                                        .filter(categories1 -> !categories1.getName().equals("Wszystkie kategorie"))
                                                        .collect(Collectors.toList()));


        return "add";
    }

    @PostMapping("/add")
    public String addProduct(Products product){
        products.add(product);
        return "redirect:/list?category=wszystko";
    }
}
