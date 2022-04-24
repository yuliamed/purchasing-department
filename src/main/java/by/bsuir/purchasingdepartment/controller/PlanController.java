package by.bsuir.purchasingdepartment.controller;

import by.bsuir.purchasingdepartment.service.PlanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value = "/plans")
@AllArgsConstructor
public class PlanController {
    @GetMapping
    public String showPlanPage(Model model) {
        return "plans";
    }
 //   private final PlanService planService;
}
