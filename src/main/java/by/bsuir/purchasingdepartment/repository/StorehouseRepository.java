package by.bsuir.purchasingdepartment.repository;

import by.bsuir.purchasingdepartment.entity.Storehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorehouseRepository extends JpaRepository<Storehouse, Long> {
}
