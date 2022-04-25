package by.bsuir.purchasingdepartment.service.impl;

import by.bsuir.purchasingdepartment.entity.Product;
import by.bsuir.purchasingdepartment.entity.Specification;
import by.bsuir.purchasingdepartment.repository.ProductRepository;
import by.bsuir.purchasingdepartment.repository.SpecificationRepository;
import by.bsuir.purchasingdepartment.service.ProductService;
import by.bsuir.purchasingdepartment.service.dto.ProductDto;
import by.bsuir.purchasingdepartment.service.exception.ResourceNotFoundException;
import by.bsuir.purchasingdepartment.service.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductImpl implements ProductService {
    private final ProductRepository productRepo;
    private final SpecificationRepository specificationRepo;
    private final ProductMapper productMapper;

    @Override
    public List<Product> findAll() {
        List<Product> products = productRepo.findAll();
        return products;
    }

    @Override
    public void deleteProduct(Long id) {
        //TODO проверка на наличие продуктов в актуальных записях плана либо настройка правильного удаления через бд
        Product delProduct = productRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("There is no resource with id= " + id));
        List<Specification> specificationList = specificationRepo.findByProduct(delProduct);
        specificationRepo.deleteAll(specificationList);
        productRepo.delete(delProduct);
    }

    @Override
    public Product addProduct(ProductDto dto) {
        Product newProduct = productMapper.toEntity(dto);
        for (Specification s : newProduct.getSpecifications())
            s.setProduct(newProduct);
        newProduct = productRepo.save(newProduct);
        return newProduct;
    }

    @Override
    public Product updateProduct(Product newProduct) {
        productRepo.save(newProduct);
        return newProduct;
    }
}
