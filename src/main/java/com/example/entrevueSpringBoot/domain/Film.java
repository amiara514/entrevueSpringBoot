package com.example.entrevueSpringBoot.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titre")
    private String titre;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "films_acteurs",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "acteur_id"))
    private Set<Acteur> acteurs;
}
