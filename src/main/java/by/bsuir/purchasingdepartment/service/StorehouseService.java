package by.bsuir.purchasingdepartment.service;

import by.bsuir.purchasingdepartment.entity.Storehouse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorehouseService {
    List<Storehouse> findAll();

    void addProductsFromFile(MultipartFile file);
}
