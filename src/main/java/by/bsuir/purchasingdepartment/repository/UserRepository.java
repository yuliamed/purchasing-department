package by.bsuir.purchasingdepartment.repository;

import by.bsuir.purchasingdepartment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
