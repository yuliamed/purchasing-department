package by.bsuir.purchasingdepartment.service.impl;

import by.bsuir.purchasingdepartment.entity.*;
import by.bsuir.purchasingdepartment.entity.enam.OrderStatus;
import by.bsuir.purchasingdepartment.repository.*;
import by.bsuir.purchasingdepartment.security.service.JwtUser;
import by.bsuir.purchasingdepartment.service.OrderService;
import by.bsuir.purchasingdepartment.service.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class OrderImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final PlanRepository planRepository;
    private final StorehouseRepository storeRepository;
    private final ResourceRepository resourceRepository;
    private final CatalogRepository catalogRepository;
    private final PaymentTypeRepository paymentTypeRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;

    @Override
    public List<RequiredResourcesDto> findRequiredResources() {
        List<Plan> plans = planRepository.findAll();
        Map<Resource, Integer> map = new HashMap<>();
        for (Plan plan : plans) {
            Product product = plan.getProduct();
            countResourcesWithSpecification(plan.getCount(), product.getSpecifications(), map);
        }
        List<RequiredResourcesDto> resSet = countNeedToBuyResource(map);

        return resSet;
    }

    @Override
    public DataForCreatingOrderDto getOrderProvidersByResId(
            CreatingOrderDto reqDto) {
        DataForCreatingOrderDto result = new DataForCreatingOrderDto();
        Resource resource = resourceRepository.getById(reqDto.getResourceId());
        List<Catalog> catalogList = catalogRepository.findByResource(resource);
        List<OrderProvidersDto> orderProvidersList = createProviderList(catalogList, reqDto.getCount());
        result.setListProviders(orderProvidersList);
        result.setCount(reqDto.getCount());
        result.setResource(resource);
        result.setPaymentTypes(paymentTypeRepository.findAll());
        return result;
    }

    @Override
    public Order createOrder(CreatingOrderDto dto) {
        Order order = new Order();
        order.setCount(dto.getCount());
        order.setPaymentType(paymentTypeRepository.getById(dto.getPaymentTypeId()));
        Catalog catalog = findCatalog(dto.getResourceId(), dto.getProviderId());
        //catalogRepository.getById(dto.getCatalogId());
        order.setCatalog(catalog);
        order.setManager(getUserFromAuth());
        order.setDeliveryDate(LocalDate.now().plusDays(catalog.getDeliveryTimeInDays()));
        Status status = statusRepository.getByName(OrderStatus.EN_ROUTE.name());
        order.setStatus(status);
        order.setIsPaid(false);
        order.setWholePrice(catalog.getPrice() * dto.getCount());
        Order savedOrder = orderRepository.save(order);
        return savedOrder;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders;
    }

    @Override
    public List<Status>getAllStatuses(){
        List<Status> statuses = statusRepository.findAll();
        return statuses;
    }

    @Override
    public List<PaymentType> getAllPaymentTypes(){
        List<PaymentType> statuses = paymentTypeRepository.findAll();
        return statuses;
    }

    @Override
    public Resource getResource(Long id){
        Resource res = resourceRepository.getById(id);
        return res;
    }

    private Catalog findCatalog(Long resourceId, Long providerId) {
        List<Catalog> catalogList = catalogRepository.findByResource(resourceRepository.getById(resourceId));
        for(Catalog c : catalogList){
            if(c.getProvider().getId().equals(providerId))
                return c;
        }
        return null;
    }



    private User getUserFromAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        if (Objects.nonNull(jwtUser)) {
            return userRepository.getById(jwtUser.getId());
        }
        return null;
    }

    private List<OrderProvidersDto> createProviderList(List<Catalog> catalogList, Integer requiredCount) {
        List<OrderProvidersDto> res = new ArrayList<>();
        for (Catalog c : catalogList) {
            OrderProvidersDto dto = new OrderProvidersDto();
            dto.setProvider(c.getProvider());
            dto.setPriceForOne(c.getPrice());
            dto.setPriceForAll(c.getPrice() * requiredCount);
            dto.setDeliveryDate(LocalDate.now().plusDays(c.getDeliveryTimeInDays()));
            res.add(dto);
        }
        return res;
    }

    private List<RequiredResourcesDto> countNeedToBuyResource(Map<Resource, Integer> mainMap) {
        List<RequiredResourcesDto> resSet = new ArrayList<>();
        Set<Resource> resourcesFromMap = mainMap.keySet();
        for (Resource r : resourcesFromMap) {
            RequiredResourcesDto dto = new RequiredResourcesDto();
            dto.setResource(r);
            dto.setRequiredResCount(mainMap.get(r));
            Storehouse store = storeRepository.findByResource(r);
            if (Objects.nonNull(store)) {
                Integer needToBuyCount = mainMap.get(r) - store.getCount() * store.getDimension().getCapacity();
                if (needToBuyCount > 0) {
                    dto.setNeedToBuyCount(needToBuyCount);
                } else {
                    dto.setNeedToBuyCount(0);
                }
            } else {
                dto.setNeedToBuyCount(mainMap.get(r));
            }
            resSet.add(dto);
        }
        return resSet;
    }

    private void countResourcesWithSpecification(Integer plansCont, List<Specification> specifications, Map<Resource, Integer> map) {
        Integer count = 0;
        for (Specification s : specifications) {
            if (map.containsKey(s.getResource())) {
                count = s.getCount() * plansCont + map.get(s.getResource());
            } else {
                count = s.getCount() * plansCont;
            }

            map.put(s.getResource(), count);
        }

    }
}
