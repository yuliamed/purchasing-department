package by.bsuir.purchasingdepartment.controller;
import by.bsuir.purchasingdepartment.entity.Storehouse;
import by.bsuir.purchasingdepartment.service.StorehouseService;
import by.bsuir.purchasingdepartment.service.UserService;
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

    @GetMapping
    public String showPlanPage(Model model) {
        List<Storehouse> s = storehouseService.findAll();
        return "store";
    }


    @PostMapping("/add")
    public String addFileWithPlan(@RequestParam("file") MultipartFile file){
        storehouseService.addProductsFromFile(file);
        return "redirect:";
    }
}
