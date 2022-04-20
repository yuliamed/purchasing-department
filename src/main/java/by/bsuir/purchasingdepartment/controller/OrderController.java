package by.bsuir.purchasingdepartment.controller;

import by.bsuir.purchasingdepartment.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders/")
@AllArgsConstructor
public class OrderController {
   // private final OrderService orderService;
}
