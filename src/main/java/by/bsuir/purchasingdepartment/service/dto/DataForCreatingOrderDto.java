package by.bsuir.purchasingdepartment.service.dto;

import by.bsuir.purchasingdepartment.entity.PaymentType;
import by.bsuir.purchasingdepartment.entity.Resource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DataForCreatingOrderDto extends AbstractDTO {
    //TODO А айди заказа точно нужен? Надо найти как можно бд стянуть следующий айдишник
    private List<OrderProvidersDto> listProviders;
    private Resource resource;
    private Integer count;
    private List<PaymentType> paymentTypes;
}
