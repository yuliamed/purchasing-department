package by.bsuir.purchasingdepartment.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//TODO удалить
@NoArgsConstructor
@Getter
@Setter
public class ResourceCountDto extends AbstractDTO{
    Long resId;
    Integer requiredCount;
}
