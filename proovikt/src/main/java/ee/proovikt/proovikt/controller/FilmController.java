package ee.proovikt.proovikt.controller;

import ee.proovikt.proovikt.entity.Film;
import ee.proovikt.proovikt.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @PostMapping("/films")
    public Film addFilm(@RequestBody Film film) {
        return filmRepository.save(film);
    }

    @DeleteMapping("/films/{id}")
    public void deleteFilm(@PathVariable Long id) {
        filmRepository.deleteById(id);
    }

    @PutMapping("/films/{id}/type")
    public Film changeType(@PathVariable Long id, @RequestBody String type) {
        Film film = filmRepository.findById(id).orElseThrow();
        film.setType(type);
        return filmRepository.save(film);
    }

    @GetMapping("/films")
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @GetMapping("/films/available")
    public List<Film> getAvailableFilms() {
        return filmRepository.findByRentedFalse();
    }
}