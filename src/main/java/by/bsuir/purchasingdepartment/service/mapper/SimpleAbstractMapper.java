package by.bsuir.purchasingdepartment.service.mapper;

import by.bsuir.purchasingdepartment.entity.AbstractEntity;
import by.bsuir.purchasingdepartment.service.dto.AbstractDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class SimpleAbstractMapper<E extends AbstractEntity, D extends AbstractDTO>
        implements SimpleObjectMapper<E, D> {

    private final Class<E> entity;
    private final Class<D> dto;

    @Autowired
    protected ModelMapper mapper;

    @Override
    public E toEntity(D d) {
        if (Objects.isNull(d)) return null;
        return mapper.map(d, entity);
    }

    @Override
    public D toDto(E e) {
        if (Objects.isNull(e)) return null;
        return mapper.map(e, dto);
    }

    @Override
    public List<D> toDtoList(List<E> entities) {
        return
                entities.stream()
                        .map(this::toDto)
                        .collect(Collectors.toList());
    }
}
