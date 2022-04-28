package by.bsuir.purchasingdepartment.service;

import by.bsuir.purchasingdepartment.entity.Plan;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PlanService {
    List<Plan> findAll();
    void addPlansFromFile(MultipartFile file);
}
