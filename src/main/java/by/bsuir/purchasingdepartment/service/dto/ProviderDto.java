package by.bsuir.purchasingdepartment.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProviderDto extends AbstractDTO{

    private String name;

    private String address;

    private Integer phNumber;

    private List<CatalogDto> catalogList;
}
