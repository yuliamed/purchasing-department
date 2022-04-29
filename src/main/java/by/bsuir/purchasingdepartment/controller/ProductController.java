package by.bsuir.purchasingdepartment.controller;

import by.bsuir.purchasingdepartment.entity.Product;
import by.bsuir.purchasingdepartment.entity.Resource;
import by.bsuir.purchasingdepartment.repository.ResourceRepository;
import by.bsuir.purchasingdepartment.service.ProductService;
import by.bsuir.purchasingdepartment.service.dto.IdsDto;
import by.bsuir.purchasingdepartment.service.dto.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ResourceRepository resourceRepository;

    @GetMapping
    public String showProductPage(Model model) {
        List<Product> products = productService.findAll();
        //это был мой большой тест кейс)
//        ProductDto dto = new ProductDto();
//        dto.setIsProduct(true);
//        dto.setName("карандаш");
//        List<SpecificationDto> specDto = new ArrayList<>();
//        SpecificationDto specificationDto = new SpecificationDto();
//        Resource resource = resourceRepository.findById(1l).orElseThrow();
//        specificationDto.setResource(resource);
//        specificationDto.setCount(10);
//        specDto.add(specificationDto);
//        dto.setSpecifications(specDto);
//        Product product = productService.addProduct(dto);
//        product.getSpecifications().get(0).setCount(111);
//        productService.updateProduct(product);
//        productService.deleteProduct(product.getId());
        model.addAttribute("products", products);
        return "products";
    }
    @DeleteMapping("/delete")
    public @ResponseBody
    String delete(IdsDto delIds, BindingResult result) {
        String returnText="удаление продуктов выполнено успешно";
        for (int i = 0;i < delIds.getDelIds().size(); i++){
            productService.deleteProduct(delIds.getDelIds().get(i));
        }
        return returnText;
    }
    @GetMapping(value = "/create-product")
    public String showProductAddPage(Model model) {
        List<Resource> resources = resourceRepository.findAll();
        model.addAttribute("new_product", new ProductDto());
        model.addAttribute("resources", resources);
        return "create-product";
    }
}
