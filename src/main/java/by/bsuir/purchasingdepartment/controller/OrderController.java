package by.bsuir.purchasingdepartment.controller;

import by.bsuir.purchasingdepartment.entity.Order;
import by.bsuir.purchasingdepartment.entity.Provider;
import by.bsuir.purchasingdepartment.entity.Resource;
import by.bsuir.purchasingdepartment.service.OrderService;
import by.bsuir.purchasingdepartment.service.dto.CreatingOrderDto;
import by.bsuir.purchasingdepartment.service.dto.DataForCreatingOrderDto;
import by.bsuir.purchasingdepartment.service.dto.RequiredResourcesDto;
import by.bsuir.purchasingdepartment.service.dto.ResourceCountDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public String showOrdersPage(Model model) {
//        List<RequiredResourcesDto> req = orderService.findRequiredResources();
//        for (RequiredResourcesDto dto : req) {
//            System.out.println(dto.getNeedToBuyCount() + " " + dto.getRequiredResCount() + " " + dto.getResource().getName());
//        }
//        Resource res = req.get(0).getResource();
//        Integer count = req.get(0).getNeedToBuyCount();
//        ResourceCountDto resourceCountDto = new ResourceCountDto();
//        resourceCountDto.setRequiredCount(count);
//        resourceCountDto.setResId(res.getId());
//        DataForCreatingOrderDto dataForCreatingOrderDto = orderService.getOrderProvidersByResId(resourceCountDto);
//
//        CreatingOrderDto creatingOrderDto = new CreatingOrderDto();
//        creatingOrderDto.setCount(count);
//        Provider provider = dataForCreatingOrderDto.getListProviders().get(0).getProvider();
//        creatingOrderDto.setProviderId(provider.getId());
//        creatingOrderDto.setResourceId(res.getId());
//        orderService.createOrder(creatingOrderDto);

        List<Order> orders = orderService.findAll();
        // TODO нужен метод получения свех возможных статусов
        // List<String> statuses =
        model.addAttribute("orders", orders);
        //model.addAttribute("statuses", statuses);

        return "orders";
    }

}
