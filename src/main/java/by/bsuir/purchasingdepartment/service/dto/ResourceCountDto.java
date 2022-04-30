package by.bsuir.purchasingdepartment.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResourceCountDto extends AbstractDTO{
    Long resId;
    Integer requiredCount;
}
