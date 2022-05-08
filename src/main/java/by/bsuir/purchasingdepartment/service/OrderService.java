package by.bsuir.purchasingdepartment.service;

import by.bsuir.purchasingdepartment.entity.Order;
import by.bsuir.purchasingdepartment.entity.PaymentType;
import by.bsuir.purchasingdepartment.entity.Status;
import by.bsuir.purchasingdepartment.service.dto.*;

import java.util.List;
import java.util.Set;

public interface OrderService {
    List<RequiredResourcesDto> findRequiredResources();
    DataForCreatingOrderDto getOrderProvidersByResId(CreatingOrderDto reqDto);
    Order createOrder(CreatingOrderDto dto);
    List<Order> findAll();
    public List<Status>getAllStatuses();
    public List<PaymentType> getAllPaymentTypes();
}
