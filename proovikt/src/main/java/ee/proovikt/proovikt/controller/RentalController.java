package ee.proovikt.proovikt.controller;

import ee.proovikt.proovikt.entity.Rental;
import ee.proovikt.proovikt.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping("/rent/{filmId}")
    public Rental rentFilm(@PathVariable Long filmId) {
        return rentalService.rentFilm(filmId);
    }

    @PostMapping("/return/{rentalId}")
    public Rental returnFilm(@PathVariable Long rentalId) {
        return rentalService.returnFilm(rentalId);
    }
}