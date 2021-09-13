package com.example.entrevueSpringBoot.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "acteurs")
public class Acteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;
}
