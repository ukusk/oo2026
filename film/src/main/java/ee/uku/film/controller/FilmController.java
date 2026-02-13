package ee.uku.film.controller;

import ee.uku.film.entity.Film;
import ee.uku.film.repository.FilmRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FilmController {

    private final FilmRepository filmRepository;

    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    // get
    @GetMapping("/films")
    public List<Film> getFilms() {
        return filmRepository.findAll();
    }

    // post
    @PostMapping("/films")
    public List<Film> addFilm(@RequestBody Film film) {
        filmRepository.save(film);
        return filmRepository.findAll();
    }

    // delete id-ga
    @DeleteMapping("/films/{id}")
    public List<Film> deleteFilm(@PathVariable Long id) {
        filmRepository.deleteById(id);
        return filmRepository.findAll();
    }
}
