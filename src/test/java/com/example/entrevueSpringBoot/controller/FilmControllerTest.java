package com.example.entrevueSpringBoot.controller;

import com.example.entrevueSpringBoot.domain.Acteur;
import com.example.entrevueSpringBoot.domain.Film;
import com.example.entrevueSpringBoot.exception.ServiceException;
import com.example.entrevueSpringBoot.service.FilmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(FilmController.class)
class FilmControllerTest {

    @MockBean
    private FilmService filmService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void getFilmById_should_return_status_200_if_found() throws Exception {
        when(filmService.findById(anyInt())).thenReturn(Optional.of(new Film()));

        this.mockMvc
                .perform(get("/api/films/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void getFilmById_should_return_film_as_content_if_found() throws Exception {
        FilmDto dto = getDtoStub();
        Film film = FilmMapper.INSTANCE.filmDtoToFilm(dto);
        when(filmService.findById(anyInt())).thenReturn(Optional.of(film));

        this.mockMvc
                .perform(get("/api/films/{id}", 1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(dto)));
    }

    @Test
    void getFilmById_should_return_status_404_if_not_found() throws Exception {
        when(filmService.findById(anyInt())).thenReturn(Optional.empty());

        this.mockMvc
                .perform(get("/api/films/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    void getFilmById_should_return_status_500_if_service_throws_exception() throws Exception {
        when(filmService.findById(anyInt())).thenThrow(new ServiceException("Test error"));

        this.mockMvc
                .perform(get("/api/films/{id}", 1))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void createFilm_should_return_status_201_if_creation_is_success() throws Exception {
        FilmDto dto = getDtoStub();
        dto.setId(null);
        dto.getActeurs().get(0).setId(null);
        Film film = getFilmStub();
        when(filmService.create(any())).thenReturn(film);
        FilmDto result = FilmMapper.INSTANCE.filmToFilmDto(film);

        this.mockMvc
                .perform(
                        post("/api/films")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    void createFilm_should_return_film_as_content_if_success() throws Exception {
        FilmDto dto = getDtoStub();
        dto.setId(null);
        dto.getActeurs().get(0).setId(null);
        Film film = getFilmStub();
        when(filmService.create(any())).thenReturn(film);

        FilmDto result = FilmMapper.INSTANCE.filmToFilmDto(film);
        this.mockMvc
                .perform(
                        post("/api/films")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(result)));
    }

    @Test
    void createFilm_should_return_status_500_if_service_throws_exception() throws Exception {
        FilmDto dto = getDtoStub();
        dto.setId(null);
        dto.getActeurs().get(0).setId(null);
        when(filmService.create(any())).thenThrow(new ServiceException("Test error"));

        this.mockMvc
                .perform(
                        post("/api/films", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isInternalServerError());
    }

    private Film getFilmStub() {
        Acteur acteur = new Acteur();
        acteur.setId(456);
        acteur.setNom("test nom");
        acteur.setPrenom("test prenom");

        Film film = new Film();
        film.setId(654);
        film.setTitre("test titre");
        film.setDescription("test description");
        film.setActeurs(Set.of(acteur));

        return film;
    }

    private FilmDto getDtoStub() {
        ActeurDto acteur = new ActeurDto();
        acteur.setId(789);
        acteur.setNom("test nom");
        acteur.setPrenom("test prenom");

        FilmDto film = new FilmDto();
        film.setId(123);
        film.setTitre("test titre");
        film.setDescription("test description");
        film.setActeurs(List.of(acteur));

        return film;
    }
}