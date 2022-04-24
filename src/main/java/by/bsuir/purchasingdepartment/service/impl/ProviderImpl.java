package by.bsuir.purchasingdepartment.service.impl;

import by.bsuir.purchasingdepartment.entity.Provider;
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

    @Override
    public Provider addProvider(ProviderDto dto) {
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
        Provider res = providerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("There is no resource with id = " + id));
        providerRepository.delete(res);
    }

    @Override
    public Provider updateProvider(Provider provider) {
        Provider res = providerRepository.save(provider);
        return res;
    }
}
