package by.bsuir.purchasingdepartment.service.dto;

import by.bsuir.purchasingdepartment.entity.Provider;
import by.bsuir.purchasingdepartment.entity.Resource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class OrderProvidersDto {
    Provider provider;
    Double priceForOne;
    Double priceForAll;
    LocalDate deliveryDate;
}
