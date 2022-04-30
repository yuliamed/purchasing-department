package by.bsuir.purchasingdepartment.service;

import by.bsuir.purchasingdepartment.entity.Order;
import by.bsuir.purchasingdepartment.service.dto.*;

import java.util.List;
import java.util.Set;

public interface OrderService {
    Set<RequiredResourcesDto> findRequiredResources();
    DataForCreatingOrderDto getOrderProvidersByResId(ResourceCountDto reqDto);
    Order createOrder(CreatingOrderDto dto);
    List<Order> findAll();
}
