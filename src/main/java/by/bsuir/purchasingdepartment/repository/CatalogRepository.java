package by.bsuir.purchasingdepartment.repository;

import by.bsuir.purchasingdepartment.entity.Catalog;
import by.bsuir.purchasingdepartment.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {
    List<Catalog> findByProvider(Provider delProvider);
}
