package by.bsuir.purchasingdepartment.service;

import by.bsuir.purchasingdepartment.service.dto.RequiredResourcesDto;

import java.util.Set;

public interface OrderService {
    Set<RequiredResourcesDto> findRequiredResources();
}
