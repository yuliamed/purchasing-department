package by.bsuir.purchasingdepartment.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResourcesDeleteDto extends AbstractDTO {
    private List<Long> delIds;
}
