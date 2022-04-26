package by.bsuir.purchasingdepartment.service.dto;

import by.bsuir.purchasingdepartment.entity.Resource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SpecificationDto extends AbstractDTO{
    private Integer count;
    //TODO может будет удобнее получать id из фронта?
    private Resource resource;
}
