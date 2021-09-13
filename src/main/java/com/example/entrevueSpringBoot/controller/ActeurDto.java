package com.example.entrevueSpringBoot.controller;

import lombok.Data;

import java.io.Serializable;

@Data
public class ActeurDto implements Serializable {
    private Integer id;
    private String nom;
    private String prenom;
}
