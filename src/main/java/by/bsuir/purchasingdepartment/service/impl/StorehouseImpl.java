package by.bsuir.purchasingdepartment.service.impl;

import by.bsuir.purchasingdepartment.entity.*;
import by.bsuir.purchasingdepartment.repository.DimensionRepository;
import by.bsuir.purchasingdepartment.repository.ProductRepository;
import by.bsuir.purchasingdepartment.repository.ResourceRepository;
import by.bsuir.purchasingdepartment.repository.StorehouseRepository;
import by.bsuir.purchasingdepartment.service.StorehouseService;
import by.bsuir.purchasingdepartment.service.dto.PlanFileDto;
import by.bsuir.purchasingdepartment.service.dto.StorehouseFileDto;
import by.bsuir.purchasingdepartment.service.exception.ResourceNotFoundException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StorehouseImpl implements StorehouseService {
    private final StorehouseRepository storehouseRepository;
    private final ResourceRepository resourceRepository;
    private final DimensionRepository dimensionRepository;
    public final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public List<Storehouse> findAll() {
        List<Storehouse> res = storehouseRepository.findAll();
        return res;
    }

    @Override
    public void addProductsFromFile(MultipartFile file) {
        File temp = null;
        try {
            temp = File.createTempFile("myTempFile_" + LocalDateTime.now(), ".txt");
            System.out.println("Temp file created : " +
                    temp.getAbsolutePath());
            file.transferTo(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Чтение файла
        Type type = new TypeToken<ArrayList<StorehouseFileDto>>() {
        }.getType();
        List<StorehouseFileDto> storeProducts = null;
        try {
            storeProducts = gson.fromJson(new FileReader(temp.getAbsolutePath()), type);
        } catch (FileNotFoundException e) {
            throw new ResourceNotFoundException("There are no temp file!");
        }
        if (Objects.nonNull(storeProducts)) {
            List<Storehouse> newPlans = parsePlanFromDto(storeProducts);
            storehouseRepository.deleteAll();
            storehouseRepository.saveAll(newPlans);
        }
    }

    private ArrayList<Storehouse> parsePlanFromDto(List<StorehouseFileDto> plans) {
        ArrayList<Storehouse> res = new ArrayList<>();
        for (StorehouseFileDto dto : plans) {
            Storehouse storehouse = new Storehouse();
            Optional<Dimension> dimension = dimensionRepository.findById(dto.getDimension());
            Optional<Resource> resource = resourceRepository.findById(dto.getResourceId());

            if (!resource.isPresent() || !dimension.isPresent()) {
                continue;
            }

            storehouse.setCount(dto.getCount());
            storehouse.setDimension(dimension.get());
            storehouse.setResource(resource.get());
            res.add(storehouse);
        }
        return res;
    }


}
