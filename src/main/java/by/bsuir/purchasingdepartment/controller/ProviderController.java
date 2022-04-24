package by.bsuir.purchasingdepartment.controller;

import by.bsuir.purchasingdepartment.entity.Provider;
import by.bsuir.purchasingdepartment.service.ProviderService;
import by.bsuir.purchasingdepartment.service.dto.ResourceDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/providers")
@AllArgsConstructor
public class ProviderController {
    private final ProviderService providerService;
    @GetMapping
    public String showProvidersPage(Model model) {
        List<Provider> list = providerService.findAll();

        model.addAttribute("providers", list);
        model.addAttribute("new_resource", new ResourceDto());
        model.addAttribute("resIdForDelete", new Object());
        return "providers";
    }


   // private final ProviderService providerService;
}
