package ee.proovikt.proovikt.repository;

import ee.proovikt.proovikt.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}