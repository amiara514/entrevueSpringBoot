package com.example.entrevueSpringBoot.repository;

import com.example.entrevueSpringBoot.domain.Film;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FilmRepositoryTest {

    @Autowired
    private FilmRepository repository;

    @Test
    void findById_should_return_entry_from_db_with_provided_id() {
        Optional<Film> film = repository.findById(1);

        assertTrue(film.isPresent());
        assertEquals(1, film.get().getId());
    }

    @Test
    void findById_should_return_empty_if_no_entity_found_with_provided_id_in_db() {
        Optional<Film> film = repository.findById(100);

        assertTrue(film.isEmpty());
    }

    @Test
    void save_should_create_entry_for_entity_in_db() {
        Film film = new Film();
        film.setTitre("Test titre");
        film.setDescription("Test description");

        Film savedFilm = repository.save(film);
        Optional<Film> foundFilm = repository.findById(savedFilm.getId());
        assertTrue(foundFilm.isPresent());
        assertNotNull(savedFilm.getId());
        assertEquals(savedFilm.getId(), foundFilm.get().getId());
    }

}