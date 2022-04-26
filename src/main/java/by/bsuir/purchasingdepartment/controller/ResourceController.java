package by.bsuir.purchasingdepartment.controller;

import by.bsuir.purchasingdepartment.entity.Resource;
import by.bsuir.purchasingdepartment.service.ResourceService;
import by.bsuir.purchasingdepartment.service.dto.ResourceDto;
import by.bsuir.purchasingdepartment.service.dto.ResourcesDeleteDto;
import by.bsuir.purchasingdepartment.service.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/resources")
@AllArgsConstructor
public class ResourceController {
    private final ResourceService resourceService;

    @GetMapping
    public String showResourcePage(Model model) {
        List<Resource> list = resourceService.findAll();
        model.addAttribute("resources", list);
        model.addAttribute("new_resource", new ResourceDto());
        model.addAttribute("resIdForDelete", new Object());
        return "resources";
    }

    @DeleteMapping("/delete")
    public @ResponseBody String delete(ResourcesDeleteDto delIds, BindingResult result) {
        String returnText="удаление ресурсов выполнено успешно";
        for (int i = 0;i < delIds.getDelIds().size(); i++){
            resourceService.deleteResource(delIds.getDelIds().get(i));
        }
        return returnText;
    }

    @PostMapping("/add")
    public String addResource(ResourceDto resourceDto) {
        Resource res = resourceService.addResource(resourceDto);
//        Resource res = resourceService.addResource(resourceDto);
        return "redirect:";
    }
    //TODO написать
    @PutMapping("/update")
    public ResponseEntity<Resource> updateRes(Resource res) {
//        res = resourceService.updateResource(res);
        Resource entity = new Resource("test-2", "test-2");
        res = resourceService.updateResource(entity);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
