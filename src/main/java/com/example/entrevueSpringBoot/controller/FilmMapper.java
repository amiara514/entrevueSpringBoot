package com.example.entrevueSpringBoot.controller;

import com.example.entrevueSpringBoot.domain.Film;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FilmMapper {

    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    FilmDto filmToFilmDto(Film film);
    Film filmDtoToFilm(FilmDto film);
}
