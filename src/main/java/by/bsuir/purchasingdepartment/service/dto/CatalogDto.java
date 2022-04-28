package by.bsuir.purchasingdepartment.service.dto;

import by.bsuir.purchasingdepartment.entity.Provider;
import by.bsuir.purchasingdepartment.entity.Resource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CatalogDto extends AbstractDTO {

    private Resource resource;

    private Provider provider;

    private Double price;

    private Integer deliveryTimeInDays;
}
