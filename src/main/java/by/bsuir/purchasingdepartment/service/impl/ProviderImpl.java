package by.bsuir.purchasingdepartment.service.impl;

import by.bsuir.purchasingdepartment.entity.Catalog;
import by.bsuir.purchasingdepartment.entity.Provider;
import by.bsuir.purchasingdepartment.entity.Resource;
import by.bsuir.purchasingdepartment.repository.CatalogRepository;
import by.bsuir.purchasingdepartment.repository.ProviderRepository;
import by.bsuir.purchasingdepartment.repository.ResourceRepository;
import by.bsuir.purchasingdepartment.service.ProviderService;
import by.bsuir.purchasingdepartment.service.dto.CatalogDto;
import by.bsuir.purchasingdepartment.service.dto.NewProviderCatalog;
import by.bsuir.purchasingdepartment.service.dto.ProviderDto;
import by.bsuir.purchasingdepartment.service.dto.SpecificationDto;
import by.bsuir.purchasingdepartment.service.exception.ResourceNotFoundException;
import by.bsuir.purchasingdepartment.service.mapper.ProviderMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProviderImpl implements ProviderService {
    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;
    private final CatalogRepository catalogRepository;
    private final ResourceRepository resourceRepository;

    @Override
    public Provider addProvider(ProviderDto dto, NewProviderCatalog newProviderCatalog) {
        List<CatalogDto> catolog = getCatalogFromLists(newProviderCatalog.getIdsRes(), newProviderCatalog.getPrices(), newProviderCatalog.getTimes());
        dto.setCatalogList(catolog);
        Provider provider = providerMapper.toEntity(dto);
        for (Catalog c : provider.getCatalogList()) {
            c.setProvider(provider);
        }
        Provider res = providerRepository.save(provider);
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

    private List<CatalogDto> getCatalogFromLists(List<Long> idsRes, List<Integer> prices, List<Integer> times) {
        List<CatalogDto> specificationDtoList = new ArrayList<>();
        for (int i = 0; i < idsRes.size(); i++) {
            CatalogDto dto = new CatalogDto();
            dto.setPrice(prices.get(i).doubleValue());
            dto.setDeliveryTimeInDays(times.get(i));

            Resource res = resourceRepository.getById(idsRes.get(i));
            dto.setResource(res);

            specificationDtoList.add(dto);
        }
        return specificationDtoList;
    }
}
