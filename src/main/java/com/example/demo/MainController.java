package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private List<Products> products;

    public MainController(){
        products = new ArrayList<>();
        products.add(new Products(1, "Czekoladki", 22.25, "src/czekoladki.png","Inne"));
        products.add(new Products(2, "Chleb", 4.85, "src/chleb.png","Artykuły spożywcze"));
        products.add(new Products(3, "Talerz 22cm", 5.20, "src/talerz.png","Artykuły gospodarstwa domowego</"));
        products.add(new Products(4, "Młotek", 22.25, "src/mlotek.png","Inne"));
    }

    @GetMapping("/")
    public String home(Model model) {

        List<String> categoriesList = new ArrayList<>();

        categoriesList.add("Artykuły spożywcze");
        categoriesList.add("Artykuły gospodarstwa domowego");
        categoriesList.add("Inne");

        model.addAttribute("categoriesList", categoriesList);

        return "home";
    }


    @GetMapping("/list")
    public String categories(Model model){

        model.addAttribute("products", products);
        return "category";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Products products  = new Products();

        model.addAttribute("product", new Products());


        return "add";
    }

    @PostMapping("/add")
    public String addProduct(Products product){
        products.add(product);
        return "redirect:/list?category=wszystko";
    }
}
