package ru.fruzbuka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fruzbuka.controller.repr.ProductRepr;
import ru.fruzbuka.service.BrandService;
import ru.fruzbuka.service.CategoryService;
import ru.fruzbuka.service.ProductService;

@Slf4j
@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public String allProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("brands", brandService.getAll());
        return "shop-grid";
    }

    @GetMapping("/product/{id}")
    public String viewProduct(@PathVariable("id") Long id, Model model) {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("brands", brandService.getAll());
        ProductRepr product = productService.getById(id).orElseThrow(() ->
                new NotFoundException("Product " + id + " not found", "Product")
        );
        log.info("View product {} ", product.getId());
        model.addAttribute("product", product);
        return "shop-details";
    }
}
