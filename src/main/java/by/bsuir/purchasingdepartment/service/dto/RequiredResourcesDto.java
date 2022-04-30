package by.bsuir.purchasingdepartment.service.dto;

import by.bsuir.purchasingdepartment.entity.Resource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequiredResourcesDto extends AbstractDTO{
    private Resource resource;
    private Integer requiredResCount;
    private Integer needToBuyCount;
}
