package by.bsuir.purchasingdepartment.service;

import by.bsuir.purchasingdepartment.entity.Provider;
import by.bsuir.purchasingdepartment.service.dto.ProviderDto;

import java.util.List;

public interface ProviderService {
    Provider addProvider(ProviderDto dto);
    List<Provider> findAll();
    void deleteProvider(Long id);
    Provider updateProvider(Provider provider);

}
