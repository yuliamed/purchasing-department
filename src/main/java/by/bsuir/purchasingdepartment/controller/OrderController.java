package by.bsuir.purchasingdepartment.controller;

import by.bsuir.purchasingdepartment.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value = "/orders")
@AllArgsConstructor
public class OrderController {
    @GetMapping
    public String showOrdersPage(Model model) {
        return "orders";
    }
   // private final OrderService orderService;
}
