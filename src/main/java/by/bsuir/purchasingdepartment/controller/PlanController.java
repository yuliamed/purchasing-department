package by.bsuir.purchasingdepartment.controller;

import by.bsuir.purchasingdepartment.service.PlanService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/plans")
@AllArgsConstructor
public class PlanController {
    private final PlanService planService;

    @GetMapping
    public String showPlanPage(Model model) {
        return "plans";
    }

    @PostMapping("/add")
    public String addFileWithPlan(@RequestParam("file")MultipartFile file){
        //String s = file.getContentType();
        planService.addPlansFromFile(file);
        return "redirect:";
    }
}
