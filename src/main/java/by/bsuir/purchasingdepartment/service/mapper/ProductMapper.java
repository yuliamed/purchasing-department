package by.bsuir.purchasingdepartment.service.mapper;

import by.bsuir.purchasingdepartment.entity.Product;
import by.bsuir.purchasingdepartment.service.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends SimpleAbstractMapper<Product, ProductDto> {
    public ProductMapper() {
        super(Product.class, ProductDto.class);
    }
}
