package by.bsuir.purchasingdepartment.controller;

import by.bsuir.purchasingdepartment.entity.Order;
import by.bsuir.purchasingdepartment.entity.Provider;
import by.bsuir.purchasingdepartment.entity.Resource;
import by.bsuir.purchasingdepartment.service.OrderService;
import by.bsuir.purchasingdepartment.service.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.jar.Attributes;

@Controller
@RequestMapping(value = "/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private List<CreatingOrderDto> creatingOrderDtos;

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

    @GetMapping(value = "/required-res")
    public String showRequiredResoucesPage(Model model) {
        this.creatingOrderDtos.clear();
        List<RequiredResourcesDto> requiredResources = orderService.findRequiredResources();
        model.addAttribute("resources", requiredResources);
        model.addAttribute("orderDto", new CreatingOrderDto());
        return "required-resources";
    }
    @PostMapping(value = "/required-res")
    public String createOrder(@ModelAttribute("orderDto") CreatingOrderDto creatingOrderDto){
        this.creatingOrderDtos.add(creatingOrderDto);
        return "redirect:create-order";
    }


    @GetMapping(value = "/create-order")
    public String showCreateOrderPage(Model model) {
     //   List<ProviderDto> providerDtos = orderService.getOrderProvidersByResId(this.creatingOrderDtos.get(0));
        model.addAttribute("order", this.creatingOrderDtos.get(0));
//        model.addAttribute("providers", )
        return "create-order";
    }
}
