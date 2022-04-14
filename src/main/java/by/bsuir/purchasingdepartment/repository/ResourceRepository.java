package by.bsuir.purchasingdepartment.repository;
import by.bsuir.purchasingdepartment.entity.Provider;
import by.bsuir.purchasingdepartment.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
