package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private List<Products> products;
    private List<Categories> categories;

    public MainController(){
        products = new ArrayList<>();
        products.add(new Products(1, "Czekoladki", 22.25, "src/czekoladki.png","Artykuły spożywcze"));
        products.add(new Products(2, "Chleb", 4.85, "src/chleb.png","Artykuły spożywcze"));
        products.add(new Products(3, "Herbatka owocowa teekannne blueberry 20x2,25gr", 3.55, "https://selgros24.pl/images/prodImages/TEEKANNE_Herbatka_owocowa_TEEKANNE_Blueberry_20_x_2_25g_32384968_0_350_350.jpg","Artykuły spożywcze"));
        products.add(new Products(4, "Talerz 22cm", 5.20, "src/talerz.png","Artykuły gospodarstwa domowego"));
        products.add(new Products(5, "Czajnik retro Amica KF4042", 158.67, "https://selgros24.pl/images/prodImages/Amica_Czajnik_Retro_Amica_KF4042_39402367_0_350_350.jpg","Artykuły gospodarstwa domowego"));
        products.add(new Products(6, "Młotek", 22.25, "src/mlotek.png","Inne"));
        products.add(new Products(7, "Projektor Overmax multipic 3.5", 478.47, "https://selgros24.pl/images/prodImages/Overmax_Projektor_Overmax_Multipic_3_5_63183925_1_350_350.jpg","Inne"));

        categories = new ArrayList<>();
        categories.add(new Categories(1, CategoryGeneral.ALL_PRODUCTS.getDescription(), true));
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

        if(selectedCategory.equals(CategoryGeneral.ALL_PRODUCTS.getDescription())){
            model.addAttribute("products", products);
            model.addAttribute("productsTotalSum", products.stream()
                                                                .mapToDouble(product -> product.getPrice())
                                                                .sum());
        }
        else {
            model.addAttribute("products", products.stream()
                                                        .filter(product -> product.getCategory().equals(selectedCategory))
                                                        .collect(Collectors.toList()));

            double sum = products.stream()
                                    .filter(product -> product.getCategory().equals(selectedCategory))
                                    .mapToDouble(product -> product.getPrice())
                                    .sum();

            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);

            model.addAttribute("productsTotalSum",df.format(sum));
        }
        return "category";
    }

    @GetMapping("/add")
    public String add(Model model) {
        //Products products  = new Products();
        model.addAttribute("newProduct", new Products());
        model.addAttribute("products", products);
        model.addAttribute("categories", categories.stream()
                                                        .filter(categories1 -> !categories1.getName().equals(CategoryGeneral.ALL_PRODUCTS.getDescription()))
                                                        .collect(Collectors.toList()));


        return "add";
    }

    @PostMapping("/add")
    public String addProduct(Products product){
        product.setId(products.stream()
                                .mapToInt(product1 -> product1.getId())
                                .max().orElseThrow(NoSuchElementException::new)+1);
        products.add(product);
        return "redirect:/list?category=" + CategoryGeneral.ALL_PRODUCTS.getDescription();
    }
}
