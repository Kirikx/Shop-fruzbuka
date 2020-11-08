package ru.fruzbuka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.fruzbuka.persist.entity.Category;
import ru.fruzbuka.service.CategoryService;

import javax.validation.Valid;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private final static Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping
    public String allCategories(Model model) {
        model.addAttribute("activePage", "Categories");
        model.addAttribute("categories", categoryService.getAll());
        return "categories";
    }

    @GetMapping("/create")
    public String createCategory(Model model) {
        logger.info("Create category");
        Category category = new Category();
        model.addAttribute("activePage", "Categories");
        model.addAttribute("category", category);
        return "category";
    }

    @GetMapping("/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getById(id).orElseThrow(() ->
                new NotFoundException("category " + id + " not found", "category")
        );
        logger.info("Edit category {} ", category);
        model.addAttribute("activePage", "Categories");
        model.addAttribute("category", category);
        return "category";
    }

    @PostMapping("/update")
    public String updateCategory(Model model, @Valid Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "category";
        }
        logger.info("Update category {} ", category);
        model.addAttribute("activePage", "Categories");
        categoryService.saveOrUpdate(category);
        return "redirect:/category";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteCategory(Model model, @PathVariable("id") Long id) {
        logger.info("Delete category by id {} ", id);
        model.addAttribute("activePage", "Categories");
        categoryService.deleteById(id);
        return "redirect:/category";
    }
}
