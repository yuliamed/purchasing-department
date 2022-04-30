package by.bsuir.purchasingdepartment.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NewProductSpecification {
    private List<Long> idsRes;
    private List<Integer> countOfRes;
}
