package by.bsuir.purchasingdepartment.service;

import by.bsuir.purchasingdepartment.entity.Resource;
import by.bsuir.purchasingdepartment.service.dto.ResourceDto;
import by.bsuir.purchasingdepartment.service.dto.ResourceUpdateDto;

import java.util.List;

public interface ResourceService {
    List<Resource> findAll();

    Resource updateResource(ResourceUpdateDto updateResource);

    Resource addResource(ResourceDto updateResource);

    void deleteResource(Long id);
}
