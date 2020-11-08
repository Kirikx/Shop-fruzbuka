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
import ru.fruzbuka.persist.entity.Order;
import ru.fruzbuka.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String allOrders(Model model) {
        model.addAttribute("activePage", "Orders");
        model.addAttribute("orders", orderService.getAll());
        return "orders";
    }

    @GetMapping("/create")
    public String creatOorder(Model model) {
        logger.info("Create order");
        Order order = new Order();
        model.addAttribute("activePage", "Orders");
        model.addAttribute("order", order);
        return "order";
    }

    @GetMapping("/{id}")
    public String editOrder(@PathVariable("id") Long id, Model model) {
        Order order = orderService.getById(id).orElseThrow(() ->
                new NotFoundException("Order " + id + " not found", "Order")
        );
        logger.info("Edit order {} ", order);
        model.addAttribute("activePage", "Orders");
        model.addAttribute("order", order);
        return "order";
    }

    @PostMapping("/update")
    public String updateOrder(Model model, Order order) {
        logger.info("Update order {} ", order);
        model.addAttribute("activePage", "Orders");
        orderService.saveOrUpdate(order);
        return "redirect:/order";
    }

    @GetMapping("/{id}/delete")
    public String deleteOrder(Model model, @PathVariable("id") Long id) {
        logger.info("Delete order by id {} ", id);
        model.addAttribute("activePage", "Orders");
        orderService.deleteById(id);
        return "redirect:/order";
    }
}
