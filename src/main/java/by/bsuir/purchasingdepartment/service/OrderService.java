package by.bsuir.purchasingdepartment.service;

import by.bsuir.purchasingdepartment.service.dto.*;

import java.util.Set;

public interface OrderService {
    Set<RequiredResourcesDto> findRequiredResources();
    DataForCreatingOrderDto getOrderProvidersByResId(ResourceCountDto reqDto);
}
