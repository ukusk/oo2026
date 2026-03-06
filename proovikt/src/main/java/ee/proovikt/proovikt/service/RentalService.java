package ee.proovikt.proovikt.service;

import ee.proovikt.proovikt.entity.Film;
import ee.proovikt.proovikt.entity.Rental;
import ee.proovikt.proovikt.repository.FilmRepository;
import ee.proovikt.proovikt.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class RentalService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private RentalRepository rentalRepository;

    public Rental rentFilm(Long filmId) {

        Film film = filmRepository.findById(filmId).orElseThrow();

        if (film.isRented()) {
            throw new RuntimeException("Film already rented");
        }

        film.setRented(true);
        filmRepository.save(film);

        Rental rental = new Rental();
        rental.setFilm(film);
        rental.setRentDate(LocalDate.now());
        rental.setPrice(5);

        return rentalRepository.save(rental);
    }

    public Rental returnFilm(Long rentalId) {

        Rental rental = rentalRepository.findById(rentalId).orElseThrow();

        rental.setReturnDate(LocalDate.now());

        long days = ChronoUnit.DAYS.between(rental.getRentDate(), rental.getReturnDate());

        if (days > 3) {
            rental.setLateFee((days - 3) * 2);
        }

        Film film = rental.getFilm();
        film.setRented(false);
        filmRepository.save(film);

        return rentalRepository.save(rental);
    }
}