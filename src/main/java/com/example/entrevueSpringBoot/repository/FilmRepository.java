package com.example.entrevueSpringBoot.repository;

import com.example.entrevueSpringBoot.domain.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface FilmRepository extends JpaRepository<Film, Integer> {

}
