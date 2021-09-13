package com.example.entrevueSpringBoot.controller;

import com.example.entrevueSpringBoot.domain.Film;
import com.example.entrevueSpringBoot.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/films")
public class FilmController {
    @Autowired
    private FilmService filmService;
    private static final FilmMapper mapper = FilmMapper.INSTANCE;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilmDto> getFilmById(@PathVariable("id") int id) {
        Optional<Film> film = filmService.findById(id);
        return film
                .map(f -> new ResponseEntity<>(mapper.filmToFilmDto(f), HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilmDto> createFilm(@RequestBody FilmDto film) {
        Film newFilm = filmService.create(mapper.filmDtoToFilm(film));

        return new ResponseEntity<>(mapper.filmToFilmDto(newFilm), HttpStatus.CREATED);
    }
}
