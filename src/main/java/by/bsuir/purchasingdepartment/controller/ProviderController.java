package by.bsuir.purchasingdepartment.controller;

import by.bsuir.purchasingdepartment.entity.Catalog;
import by.bsuir.purchasingdepartment.entity.Provider;
import by.bsuir.purchasingdepartment.service.ProviderService;
import by.bsuir.purchasingdepartment.service.dto.IdsDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/providers")
@AllArgsConstructor
public class ProviderController {
    private final ProviderService providerService;
    private List<Catalog> catalogs;
    @GetMapping
    public String showProvidersPage(Model model) {
        List<Provider> list = providerService.findAll();

        model.addAttribute("providers", list);
        model.addAttribute("provider", new IdsDto());
       // model.addAttribute("catalog", provider.getCatalogList());

        if (catalogs.size()>0){
            Provider p = catalogs.get(0).getProvider();
            model.addAttribute("catalog", catalogs);
            model.addAttribute("prov", p);}
//        model.addAttribute("new_resource", new ResourceDto());
//        model.addAttribute("resIdForDelete", new Object());
        return "providers";
    }
    @DeleteMapping("/delete")
    public @ResponseBody
    String delete(IdsDto delIds, BindingResult result) {
        String returnText="удаление поставщиков выполнено успешно";
        for (int i = 0;i < delIds.getDelIds().size(); i++){
            providerService.deleteProvider(delIds.getDelIds().get(i));
        }
        return returnText;
    }

    @PostMapping("/get-catalog")
    public String getCatalog(@ModelAttribute("provider") IdsDto p){
        String returnText="Для Отображения номенклатуры выбранного поставщика обновите пожалуйста страницу";
        this.catalogs = providerService.getProviderById(p.getId()).getCatalogList();
        return "redirect:";
    }
//    @GetMapping("/show-catalog")
//    public String showCatalog(Model model){
//        model.addAttribute("catal", this.catalogs);
//        return "catalog";
//    }


   // private final ProviderService providerService;
}
