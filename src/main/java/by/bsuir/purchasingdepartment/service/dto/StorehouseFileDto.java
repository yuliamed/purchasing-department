package by.bsuir.purchasingdepartment.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class StorehouseFileDto {
    private Long resourceId;
    private Integer count;
    private Long dimension;
}
