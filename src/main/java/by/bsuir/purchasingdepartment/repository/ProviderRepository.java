package by.bsuir.purchasingdepartment.repository;

import by.bsuir.purchasingdepartment.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
