package by.bsuir.purchasingdepartment.controller;
import by.bsuir.purchasingdepartment.service.StorehouseService;
import by.bsuir.purchasingdepartment.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value = "/store")
@AllArgsConstructor
public class StorehouseController {
    @GetMapping
    public String showPlanPage(Model model) {
        return "store";
    }
 //   private final StorehouseService storehouseService;
}
