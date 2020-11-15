package ru.fruzbuka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fruzbuka.exceptions.NotFoundException;
import ru.fruzbuka.persist.entity.OrderItem;
import ru.fruzbuka.service.OrderItemService;

@Controller
@RequestMapping("/order-item")
public class OrderItemController {

    private final static Logger logger = LoggerFactory.getLogger(OrderItemController.class);

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public String allOrderItems(Model model) {
        model.addAttribute("activePage", "OrderItems");
        model.addAttribute("orderItems", orderItemService.getAll());
        return "order_items";
    }

    @GetMapping("/create")
    public String creatOrderItem(Model model) {
        logger.info("Create orderItem");
        OrderItem orderItem = new OrderItem();
        model.addAttribute("activePage", "OrderItems");
        model.addAttribute("orderItem", orderItem);
        return "order_item";
    }

    @GetMapping("/{id}")
    public String editOrderItem(@PathVariable("id") Long id, Model model) {
        OrderItem orderItem = orderItemService.getById(id).orElseThrow(() ->
                new NotFoundException("Order item " + id + " not found", "OrderItem")
        );
        logger.info("Edit order item {} ", orderItem);
        model.addAttribute("activePage", "OrderItems");
        model.addAttribute("orderItem", orderItem);
        return "order_item";
    }

    @PostMapping("/update")
    public String updateOrderItem(Model model, OrderItem orderItem) {
        logger.info("Update order item {} ", orderItem);
        model.addAttribute("activePage", "OrderItems");
        orderItemService.saveOrUpdate(orderItem);
        return "redirect:/order_item";
    }

    @GetMapping("/{id}/delete")
    public String deleteOrderItem(Model model, @PathVariable("id") Long id) {
        logger.info("Delete order item by id {} ", id);
        model.addAttribute("activePage", "OrderItems");
        orderItemService.deleteById(id);
        return "redirect:/order_item";
    }
}
