package com.example.entrevueSpringBoot.service;

import com.example.entrevueSpringBoot.domain.Film;
import com.example.entrevueSpringBoot.exception.ServiceException;
import com.example.entrevueSpringBoot.repository.FilmRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FilmServiceTest {

    @MockBean
    private FilmRepository repository;

    @Autowired
    private FilmService filmService;

    @Test
    void findById_should_return_film_found_in_repository() {
        Film film = new Film();
        film.setId(123);
        when(repository.findById(anyInt())).thenReturn(Optional.of(film));

        assertEquals(Optional.of(film), filmService.findById(123));
    }

    @Test
    void findById_should_return_optional_empty_if_film_not_found_in_repository() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());

        assertEquals(Optional.empty(), filmService.findById(123));
    }

    @Test
    void findById_should_throw_service_exception_if_call_to_repository_fails() {
        when(repository.findById(anyInt())).thenThrow(new RuntimeException("Test error"));

        assertThrows(ServiceException.class, () -> filmService.findById(123));
    }

    @Test
    void create_should_return_film_found_in_repository() {
        Film film = new Film();
        film.setId(123);
        film.setTitre("Test title 2");
        when(repository.save(any())).thenReturn(film);

        Film filmToSave = new Film();
        filmToSave.setTitre("Test title");
        assertEquals(film, filmService.create(filmToSave));
    }

    @Test
    void create_should_throw_service_exception_if_call_to_repository_fails() {
        when(repository.save(any())).thenThrow(new RuntimeException("Test error"));

        Film filmToSave = new Film();
        filmToSave.setTitre("Test title");
        assertThrows(ServiceException.class, () -> filmService.create(filmToSave));
    }
}