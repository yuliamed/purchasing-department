package by.bsuir.purchasingdepartment.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ProductDto extends AbstractDTO{
    private String name;
    private Boolean isProduct;
    private List<SpecificationDto> specifications;
}
