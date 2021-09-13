package com.example.entrevueSpringBoot.controller;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FilmDto implements Serializable {
    private Integer id;
    private String titre;
    private String description;
    private List<ActeurDto> acteurs;
}
