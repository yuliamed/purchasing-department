package by.bsuir.purchasingdepartment.repository;

import by.bsuir.purchasingdepartment.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
}
