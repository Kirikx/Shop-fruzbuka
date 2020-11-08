package ru.fruzbuka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fruzbuka.controller.repr.ProductRepr;
import ru.fruzbuka.service.BrandService;
import ru.fruzbuka.service.CategoryService;
import ru.fruzbuka.service.ProductService;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String allProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        return "shop-grid";
    }

    @GetMapping("/create")
    public String createProduct(Model model) {
        log.info("Create product");
        ProductRepr product = new ProductRepr();
        model.addAttribute("product", product);
        model.addAttribute("brands", brandService.getAll());
        model.addAttribute("categories", categoryService.getAll());
        return "product";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        ProductRepr product = productService.getById(id).orElseThrow(() ->
                new NotFoundException("Product " + id + " not found", "Product")
        );
        log.info("Edit product {} ", product.getId());
        model.addAttribute("product", product);
        return "shop-details";
    }

    @PostMapping("/update")
    public String updateProduct(ProductRepr product) throws IOException {
        log.info("Update product {} ", product);
        productService.saveOrUpdate(product);
        return "redirect:/product";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") Long id) {
        log.info("Delete product by id {} ", id);
        productService.deleteById(id);
        return "redirect:/product";
    }
}
