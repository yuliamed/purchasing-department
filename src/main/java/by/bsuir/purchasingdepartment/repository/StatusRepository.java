package by.bsuir.purchasingdepartment.repository;
import by.bsuir.purchasingdepartment.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
}
