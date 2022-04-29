package by.bsuir.purchasingdepartment.service.impl;

import by.bsuir.purchasingdepartment.entity.Catalog;
import by.bsuir.purchasingdepartment.entity.Provider;
import by.bsuir.purchasingdepartment.repository.CatalogRepository;
import by.bsuir.purchasingdepartment.repository.ProviderRepository;
import by.bsuir.purchasingdepartment.service.ProviderService;
import by.bsuir.purchasingdepartment.service.dto.ProviderDto;
import by.bsuir.purchasingdepartment.service.exception.ResourceNotFoundException;
import by.bsuir.purchasingdepartment.service.mapper.ProviderMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ProviderImpl implements ProviderService {
    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;
    private final CatalogRepository catalogRepository;

    @Override
    public Provider addProvider(ProviderDto dto) {
        Provider provider = providerMapper.toEntity(dto);
        for(Catalog c: provider.getCatalogList()){
            c.setProvider(provider);
        }
        Provider res = providerRepository.save(providerMapper.toEntity(dto));
        return res;
    }

    @Override
    public List<Provider> findAll() {
        List<Provider> res = providerRepository.findAll();
        return res;
    }

    @Override
    public void deleteProvider(Long id) {
        //TODO проверка на наличие поставщика в актуальных записях заказов либо настройка правильного удаления через бд
        Provider delProvider = providerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("There is no Provider with id= " + id));
        List<Catalog> catalogList = catalogRepository.findByProvider(delProvider);
        catalogRepository.deleteAll(catalogList);
        providerRepository.delete(delProvider);
    }

    @Override
    public Provider updateProvider(Provider provider) {
        Provider res = providerRepository.save(provider);
        return res;
    }

    @Override
    public Provider getProviderById(Long id) {
        Provider res = providerRepository.getById(id);
        return res;
    }
}
