package by.bsuir.purchasingdepartment.controller;

import by.bsuir.purchasingdepartment.entity.Provider;
import by.bsuir.purchasingdepartment.service.ProviderService;
import by.bsuir.purchasingdepartment.service.dto.DeleteDto;
import by.bsuir.purchasingdepartment.service.dto.ResourceDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
//        model.addAttribute("new_resource", new ResourceDto());
//        model.addAttribute("resIdForDelete", new Object());
        return "providers";
    }
    @DeleteMapping("/delete")
    public @ResponseBody
    String delete(DeleteDto delIds, BindingResult result) {
        String returnText="удаление поставщиков выполнено успешно";
        for (int i = 0;i < delIds.getDelIds().size(); i++){
            providerService.deleteProvider(delIds.getDelIds().get(i));
        }
        return returnText;
    }


   // private final ProviderService providerService;
}
