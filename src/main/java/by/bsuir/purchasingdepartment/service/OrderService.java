package by.bsuir.purchasingdepartment.service;

import by.bsuir.purchasingdepartment.entity.Order;
import by.bsuir.purchasingdepartment.entity.PaymentType;
import by.bsuir.purchasingdepartment.entity.Resource;
import by.bsuir.purchasingdepartment.entity.Status;
import by.bsuir.purchasingdepartment.service.dto.CreatingOrderDto;
import by.bsuir.purchasingdepartment.service.dto.DataForCreatingOrderDto;
import by.bsuir.purchasingdepartment.service.dto.RequiredResourcesDto;

import java.util.List;

public interface OrderService {
    List<RequiredResourcesDto> findRequiredResources();

    DataForCreatingOrderDto getOrderProvidersByResId(CreatingOrderDto reqDto);

    Order createOrder(CreatingOrderDto dto);

    List<Order> findAll();

    public List<Status> getAllStatuses();

    public List<PaymentType> getAllPaymentTypes();

    Resource getResource(Long id);

    List<Order> getUnpaidOrders();

    void changeIsPaidStatus(List<Long> ids);

    List<Order> getDeliveredOrders();

    void confrirmArrivedResources(List<Long> ordersIds);
}
