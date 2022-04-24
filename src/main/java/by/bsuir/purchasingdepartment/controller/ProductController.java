package by.bsuir.purchasingdepartment.controller;

import by.bsuir.purchasingdepartment.entity.Resource;
import by.bsuir.purchasingdepartment.service.ProductService;
import by.bsuir.purchasingdepartment.service.dto.ResourceDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping(value = "/products")
@AllArgsConstructor
public class ProductController {
    @GetMapping
    public String showProductPage(Model model) {
        return "products";
    }
  //  private final ProductService productService;
}
