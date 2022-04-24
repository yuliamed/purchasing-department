package by.bsuir.purchasingdepartment.service.mapper;

import by.bsuir.purchasingdepartment.entity.Resource;
import by.bsuir.purchasingdepartment.service.dto.ResourceDto;
import org.springframework.stereotype.Component;

@Component
public class ResourceMapper extends SimpleAbstractMapper<Resource, ResourceDto> {
    public ResourceMapper() {
        super(Resource.class, ResourceDto.class);
    }
}
