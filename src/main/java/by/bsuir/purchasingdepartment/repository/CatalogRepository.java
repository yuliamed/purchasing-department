package by.bsuir.purchasingdepartment.repository;

import by.bsuir.purchasingdepartment.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {
}
