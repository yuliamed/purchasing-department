package by.bsuir.purchasingdepartment.service.impl;

import by.bsuir.purchasingdepartment.entity.Dimension;
import by.bsuir.purchasingdepartment.entity.Plan;
import by.bsuir.purchasingdepartment.entity.Product;
import by.bsuir.purchasingdepartment.repository.DimensionRepository;
import by.bsuir.purchasingdepartment.repository.PlanRepository;
import by.bsuir.purchasingdepartment.repository.ProductRepository;
import by.bsuir.purchasingdepartment.service.PlanService;
import by.bsuir.purchasingdepartment.service.dto.PlanFileDto;
import by.bsuir.purchasingdepartment.service.exception.ResourceNotFoundException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlanImpl implements PlanService {
    private final PlanRepository planRepository;
    private final ProductRepository productRepository;
    private final DimensionRepository dimensionRepository;
    public final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public List<Plan> findAll() {
        return planRepository.findAll();
    }

    @SneakyThrows
    @Override
    public void addPlansFromFile(MultipartFile file) {
        File temp = null;
        try {
            temp = File.createTempFile("myTempFile", ".txt");
            System.out.println("Temp file created : " +
                    temp.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Создание файла для теста и формы
//        Product product = productRepository.getById(1l);
//        Dimension dimension = dimensionRepository.getById(1l);
//        PlanFileDto testPlan = new PlanFileDto(10, LocalDate.now().toString(),product.getId(),dimension.getId());
//        File testFile=new File("C:\\University\\purchasing-department\\plans\\" + "test" + ".json");
//        String testJson = gson.toJson(testPlan);
//        FileWriter fw = new FileWriter(testFile);
//        fw.write(testJson);
//        fw.flush();
//        fw.close();

        //Чтение файла
        Type type = new TypeToken<ArrayList<PlanFileDto>>() {
        }.getType();
        List<PlanFileDto> plans = null;
        try {
            plans = gson.fromJson(new FileReader(temp.getAbsolutePath()), type);
        } catch (FileNotFoundException e) {
            throw new ResourceNotFoundException("There are no temp file!");
        }
        if (Objects.nonNull(plans)) {
            List<Plan> newPlans = parsePlanFromDto(plans);
            planRepository.saveAll(newPlans);
        }
    }

    private ArrayList<Plan> parsePlanFromDto(List<PlanFileDto> plans) {
        ArrayList<Plan> res = new ArrayList<>();
        for (PlanFileDto dto : plans) {
            Plan plan = new Plan();
            Optional<Dimension> dimension = dimensionRepository.findById(dto.getDimension());
            Optional<Product> product = productRepository.findById(dto.getProduct());
            LocalDate date = LocalDate.parse(dto.getDate());

            if (date.isBefore(LocalDate.now()) || !product.isPresent() || !dimension.isPresent()) {
                continue;
            }

            plan.setCount(dto.getCount());
            plan.setDimension(dimension.get());
            plan.setProduct(product.get());
            plan.setDate(date);
            res.add(plan);
        }
        return res;
    }
}
