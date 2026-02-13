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

    // GET
    @GetMapping("/films")
    public List<Film> getFilms() {
        return filmRepository.findAll();
    }

    // POST
    @PostMapping("/films")
    public Film addFilm(@RequestBody Film film) {
        return filmRepository.save(film);
    }

    // DELETE id-ga
    @DeleteMapping("/films/{id}")
    public void deleteFilm(@PathVariable Long id) {
        filmRepository.deleteById(id);
    }
}
