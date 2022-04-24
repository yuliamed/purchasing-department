package by.bsuir.purchasingdepartment.controller;

import by.bsuir.purchasingdepartment.entity.Resource;
import by.bsuir.purchasingdepartment.service.ResourceService;
import by.bsuir.purchasingdepartment.service.dto.ResourceDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/resource")
@AllArgsConstructor
public class ResourceController {
    private final ResourceService resourceService;

    @GetMapping("/")
    public ResponseEntity<List<Resource>> findAll() {
        List<Resource> list = resourceService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> delete(@RequestBody Long id) {
        resourceService.deleteResource(id);
        return new ResponseEntity<>("deleted res = " + id, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/")
    public ResponseEntity<Resource> addResource(ResourceDto resourceDto) {
        ResourceDto dto = new ResourceDto("rest-fuul", "eprogjeprogjeprgjeprogj");
        Resource res = resourceService.addResource(dto);
//        Resource res = resourceService.addResource(resourceDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Resource> updateRes(Resource res) {
//        res = resourceService.updateResource(res);
        Resource entity = new Resource("test-2", "test-2");
        res = resourceService.updateResource(entity);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
