package by.bsuir.purchasingdepartment.service;

import by.bsuir.purchasingdepartment.entity.Product;
import by.bsuir.purchasingdepartment.service.dto.NewProductSpecification;
import by.bsuir.purchasingdepartment.service.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void deleteProduct(Long id);

    Product addProduct(ProductDto productDto, NewProductSpecification dto);

    Product updateProduct(Product newProduct);

}
