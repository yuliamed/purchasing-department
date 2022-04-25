package by.bsuir.purchasingdepartment.repository;

import by.bsuir.purchasingdepartment.entity.Product;
import by.bsuir.purchasingdepartment.entity.Resource;
import by.bsuir.purchasingdepartment.entity.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Long> {
    List<Specification> findByProduct(Product product);
}
