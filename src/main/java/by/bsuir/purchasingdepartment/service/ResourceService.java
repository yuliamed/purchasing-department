package by.bsuir.purchasingdepartment.service;

import by.bsuir.purchasingdepartment.entity.Resource;
import by.bsuir.purchasingdepartment.service.dto.ResourceDto;

import java.util.List;

public interface ResourceService {
    List<Resource> findAll();

    Resource updateResource(Resource updateResource);

    Resource addResource(ResourceDto updateResource);

    void deleteResource(Long id);
}
