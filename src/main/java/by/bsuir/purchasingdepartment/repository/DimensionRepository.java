package by.bsuir.purchasingdepartment.repository;

import by.bsuir.purchasingdepartment.entity.Dimension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DimensionRepository extends JpaRepository<Dimension, Long> {
}
