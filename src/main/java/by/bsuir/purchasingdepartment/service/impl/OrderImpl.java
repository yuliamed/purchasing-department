package by.bsuir.purchasingdepartment.service.impl;

import by.bsuir.purchasingdepartment.entity.*;
import by.bsuir.purchasingdepartment.repository.OrderRepository;
import by.bsuir.purchasingdepartment.repository.PlanRepository;
import by.bsuir.purchasingdepartment.repository.StorehouseRepository;
import by.bsuir.purchasingdepartment.service.OrderService;
import by.bsuir.purchasingdepartment.service.dto.RequiredResourcesDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class OrderImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final PlanRepository planRepository;
    private final StorehouseRepository storeRepository;

    @Override
    public Set<RequiredResourcesDto> findRequiredResources() {
        List<Plan> plans = planRepository.findAll();
        Map<Resource, Integer> map = new HashMap<>();
        for (Plan plan : plans) {
            Product product = plan.getProduct();
            countResourcesWithSpecification(plan.getCount(), product.getSpecifications(), map);
        }
        Set<RequiredResourcesDto> resSet = countNeedToBuyResource(map);

        return resSet;
    }

    private Set<RequiredResourcesDto> countNeedToBuyResource(Map<Resource, Integer> mainMap) {
        Set<RequiredResourcesDto> resSet = new HashSet<>();
        Set<Resource> resourcesFromMap = mainMap.keySet();
        for (Resource r : resourcesFromMap) {
            RequiredResourcesDto dto = new RequiredResourcesDto();
            dto.setResource(r);
            dto.setRequiredResCount(mainMap.get(r));
            Storehouse store = storeRepository.findByResource(r);
            if (Objects.nonNull(store)) {
                Integer needToBuyCount = mainMap.get(r) - store.getCount()*store.getDimension().getCapacity();
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
