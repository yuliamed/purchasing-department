package by.bsuir.purchasingdepartment.service.mapper;

import by.bsuir.purchasingdepartment.entity.Provider;
import by.bsuir.purchasingdepartment.service.dto.ProviderDto;
import org.springframework.stereotype.Component;

@Component
public class ProviderMapper extends SimpleAbstractMapper<Provider, ProviderDto>{
    public ProviderMapper() {
        super(Provider.class, ProviderDto.class);
    }
}
