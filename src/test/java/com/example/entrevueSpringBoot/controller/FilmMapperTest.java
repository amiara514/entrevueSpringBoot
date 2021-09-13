package com.example.entrevueSpringBoot.controller;

import com.example.entrevueSpringBoot.domain.Acteur;
import com.example.entrevueSpringBoot.domain.Film;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilmMapperTest {

    @Test
    void filmToFilmDto_should_map_domain_object_to_dto() {
        Acteur acteur = new Acteur();
        acteur.setId(987);
        acteur.setNom("Test nom");
        acteur.setPrenom("Test prenom");

        Film film = new Film();
        film.setId(123);
        film.setTitre("Test titre");
        film.setDescription("Test description");
        film.setActeurs(Set.of(acteur));

        FilmDto dto = FilmMapper.INSTANCE.filmToFilmDto(film);

        assertEquals(film.getId(), dto.getId());
        assertEquals(film.getTitre(), dto.getTitre());
        assertEquals(film.getDescription(), dto.getDescription());
        assertEquals(acteur.getId(), dto.getActeurs().get(0).getId());
        assertEquals(acteur.getNom(), dto.getActeurs().get(0).getNom());
        assertEquals(acteur.getPrenom(), dto.getActeurs().get(0).getPrenom());
    }

    @Test
    void filmDtoToFilm_should_map_dto_to_domain_object() {
        ActeurDto acteurDto = new ActeurDto();
        acteurDto.setId(987);
        acteurDto.setNom("Test nom");
        acteurDto.setPrenom("Test prenom");

        FilmDto dto = new FilmDto();
        dto.setId(123);
        dto.setTitre("Test titre");
        dto.setDescription("Test description");
        dto.setActeurs(List.of(acteurDto));

        Film film = FilmMapper.INSTANCE.filmDtoToFilm(dto);
        Acteur acteur = film.getActeurs().stream().findFirst().get();

        assertEquals(dto.getId(), film.getId());
        assertEquals(dto.getTitre(), film.getTitre());
        assertEquals(dto.getDescription(), film.getDescription());
        assertEquals(acteurDto.getId(), acteur.getId());
        assertEquals(acteurDto.getNom(), acteur.getNom());
        assertEquals(acteurDto.getPrenom(), acteur.getPrenom());
    }
}