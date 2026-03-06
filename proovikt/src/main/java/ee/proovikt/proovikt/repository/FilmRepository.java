package ee.proovikt.proovikt.repository;

import ee.proovikt.proovikt.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {

    List<Film> findByRentedFalse();

}