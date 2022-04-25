package by.bsuir.purchasingdepartment.service;

import by.bsuir.purchasingdepartment.entity.Product;
import by.bsuir.purchasingdepartment.service.dto.ProductDto;
import by.bsuir.purchasingdepartment.service.dto.SpecificationDto;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void deleteProduct(Long id);

    Product addProduct(ProductDto dto);

    Product updateProduct(Product newProduct);
}
