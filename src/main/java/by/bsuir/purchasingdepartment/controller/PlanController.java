package by.bsuir.purchasingdepartment.controller;

import by.bsuir.purchasingdepartment.entity.Plan;
import by.bsuir.purchasingdepartment.service.PlanService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(value = "/plans")
@AllArgsConstructor
public class PlanController {
    private final PlanService planService;

    @GetMapping
    public String showPlanPage(Model model) {
        List<Plan> plans = planService.findAll();
        model.addAttribute("plans", plans);
        return "plans";
    }

    @PostMapping("/add")
    public String addFileWithPlan(@RequestParam("file")MultipartFile file){
        planService.addPlansFromFile(file);
        return "redirect:";
    }
}
