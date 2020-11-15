package ru.fruzbuka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fruzbuka.service.CartService;
import ru.fruzbuka.service.ProductService;
import ru.fruzbuka.service.dto.LineItem;

@RequestMapping("/cart")
@Controller
public class CartController {

    private final CartService cartService;

    private final ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public String cartPage(Model model) {
        model.addAttribute("lineItems", cartService.getLineItems());
        model.addAttribute("subTotal", cartService.getSubTotal());
        return "shoping-cart";
    }

    @PostMapping
    public String updateCart(LineItem lineItem) {
        lineItem.setProductRepr(productService.getById(lineItem.getProductId())
                .orElseThrow(IllegalArgumentException::new)); //TODO Change exception
        cartService.updateCart(lineItem);
        return "redirect:/cart";
    }

    @DeleteMapping
    public String deleteLineItem(LineItem lineItem) {
        cartService.removeProduct(lineItem);
        return "redirect:/cart";
    }
}

