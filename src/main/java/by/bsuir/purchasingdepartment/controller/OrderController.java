package by.bsuir.purchasingdepartment.controller;

import by.bsuir.purchasingdepartment.service.OrderService;
import by.bsuir.purchasingdepartment.service.dto.RequiredResourcesDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Controller
@RequestMapping(value = "/orders")
@AllArgsConstructor
public class OrderController {
    @GetMapping
    public String showOrdersPage(Model model) {
        Set<RequiredResourcesDto> req = orderService.findRequiredResources();
        for(RequiredResourcesDto dto : req){
            System.out.println(dto.getNeedToBuyCount() + " " + dto.getRequiredResCount() + " " + dto.getResource().getName());
        }
        return "orders";
    }
    private final OrderService orderService;
}
