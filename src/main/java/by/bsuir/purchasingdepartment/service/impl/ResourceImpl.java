package by.bsuir.purchasingdepartment.service.impl;

import by.bsuir.purchasingdepartment.entity.Resource;
import by.bsuir.purchasingdepartment.repository.ResourceRepository;
import by.bsuir.purchasingdepartment.service.ResourceService;
import by.bsuir.purchasingdepartment.service.dto.ResourceDto;
import by.bsuir.purchasingdepartment.service.dto.ResourceUpdateDto;
import by.bsuir.purchasingdepartment.service.exception.ResourceNotFoundException;
import by.bsuir.purchasingdepartment.service.mapper.ResourceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ResourceImpl implements ResourceService {
    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;

    @Override
    public List<Resource> findAll() {
        List<Resource> list = resourceRepository.findAll();
        return list;
    }

    @Override
    public Resource updateResource(ResourceUpdateDto updateResource) {
        Long id = Long.valueOf(updateResource.getId());
        Resource res = resourceRepository.getById(id);
        res.setDescription(updateResource.getDescription());
        res.setName(updateResource.getName());
        return resourceRepository.save(res);
    }

    @Override
    public Resource addResource(ResourceDto newResource) {
        Resource resource = resourceMapper.toEntity(newResource);
        resource = resourceRepository.save(resource);
        return resource;
    }

    @Override
    public void deleteResource(Long id) {
        Resource res = resourceRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("there are no resource with such id = " + id));
        resourceRepository.delete(res);
    }
}
