package by.bsuir.purchasingdepartment.repository;

import by.bsuir.purchasingdepartment.entity.Dimension;
import by.bsuir.purchasingdepartment.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
