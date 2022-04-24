package by.bsuir.purchasingdepartment.controller;

import by.bsuir.purchasingdepartment.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value = "/providers")
@AllArgsConstructor
public class ProviderController {
    @GetMapping
    public String showProvidersPage(Model model) {
        return "providers";
    }
   // private final ProviderService providerService;
}
