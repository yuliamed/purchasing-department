package by.bsuir.purchasingdepartment.controller;
import by.bsuir.purchasingdepartment.entity.Order;
import by.bsuir.purchasingdepartment.entity.Plan;
import by.bsuir.purchasingdepartment.entity.Storehouse;
import by.bsuir.purchasingdepartment.service.OrderService;
import by.bsuir.purchasingdepartment.service.StorehouseService;
import by.bsuir.purchasingdepartment.service.UserService;
import by.bsuir.purchasingdepartment.service.dto.IdsDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(value = "/store")
@AllArgsConstructor
public class StorehouseController {
    private final StorehouseService storehouseService;
    private final OrderService orderService;

    @GetMapping
    public String showPlanPage(Model model) {
        List<Storehouse> s = storehouseService.findAll();
        model.addAttribute("stores", s);
        return "store";
    }


    @PostMapping("/add")
    public String addFileWithPlan(@RequestParam("file") MultipartFile file){
        storehouseService.addProductsFromFile(file);
        return "redirect:";
    }
    @GetMapping(value = "/register-resource")
    public String showConfirmOrderArrivePage(Model model) {
        List<Order> plans = orderService.getDeliveredOrders();
        model.addAttribute("orders", plans);
        model.addAttribute("orderIds1", new IdsDto());
        return "register-production";
    }
    @PostMapping(value = "/register-resource/reg")
    public String register(@ModelAttribute("orderIds1") IdsDto idsDto) {
//        List<Order> plans = orderService.getDeliveredOrders();
        orderService.confrirmArrivedResources(idsDto.getDelIds());
        return "redirect:/store";
    }
}
