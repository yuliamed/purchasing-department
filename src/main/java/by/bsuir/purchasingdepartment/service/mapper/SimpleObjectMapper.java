package by.bsuir.purchasingdepartment.service.mapper;

import by.bsuir.purchasingdepartment.entity.AbstractEntity;
import by.bsuir.purchasingdepartment.service.dto.AbstractDTO;

import java.util.List;

public interface SimpleObjectMapper <E extends AbstractEntity, D extends AbstractDTO>{
    E toEntity(final D d);

    D toDto(final E e);

    List<D> toDtoList(final List<E> eList);
}
