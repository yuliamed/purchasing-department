package by.bsuir.purchasingdepartment.controller;

import by.bsuir.purchasingdepartment.entity.Resource;
import by.bsuir.purchasingdepartment.service.ResourceService;
import by.bsuir.purchasingdepartment.service.dto.ResourceDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/resource")
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
    public String delete(@ModelAttribute("resIdForDelete") String idS) {
        Integer id = Integer.parseInt(idS);
        //resourceService.deleteResource(id);
        return "redirect:";
    }

    @PostMapping("/add")
    public String addResource(ResourceDto resourceDto) {
        Resource res = resourceService.addResource(resourceDto);
//        Resource res = resourceService.addResource(resourceDto);
        return "redirect:";
    }

    @PutMapping("/update")
    public ResponseEntity<Resource> updateRes(Resource res) {
//        res = resourceService.updateResource(res);
        Resource entity = new Resource("test-2", "test-2");
        res = resourceService.updateResource(entity);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
