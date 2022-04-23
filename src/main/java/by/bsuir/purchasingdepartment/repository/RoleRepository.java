package by.bsuir.purchasingdepartment.repository;

import by.bsuir.purchasingdepartment.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);

    Role getByName(String user);
}
