package ru.fruzbuka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.fruzbuka.persist.entity.Brand;
import ru.fruzbuka.service.BrandService;

import javax.validation.Valid;

@Controller
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    private final static Logger logger = LoggerFactory.getLogger(BrandController.class);

    @GetMapping
    public String allBrands(Model model) {
        model.addAttribute("brands", brandService.getAll());
        return "brands";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        logger.info("Create brand");
        Brand brand = new Brand();
        model.addAttribute("brand", brand);
        return "brand";
    }

    @GetMapping("/{id}")
    public String editBrand(@PathVariable("id") Long id, Model model) {
        Brand brand = brandService.getById(id).orElseThrow(() ->
                new NotFoundException("Brand " + id + " not found", "Brand")
        );
        logger.info("Edit brand {} ", brand);
        model.addAttribute("brand", brand);
        return "brand";
    }

    @PostMapping("/update")
    public String updateBrand(@Valid Brand brand, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "brand";
        }
        logger.info("Update brand {} ", brand);
        brandService.saveOrUpdate(brand);
        return "redirect:/brand";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteBrand(@PathVariable("id") Long id) {
        logger.info("Delete brand by id {} ", id);
        brandService.deleteById(id);
        return "redirect:/brand";
    }
}
