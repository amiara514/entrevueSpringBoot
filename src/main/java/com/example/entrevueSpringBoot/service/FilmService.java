package com.example.entrevueSpringBoot.service;

import com.example.entrevueSpringBoot.domain.Film;
import com.example.entrevueSpringBoot.exception.ServiceException;
import com.example.entrevueSpringBoot.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    public Optional<Film> findById(int id) {
        try {
            return filmRepository.findById(id);
        } catch (Exception e) {
            // log error
            throw new ServiceException("Erreur lors de la recherche de film par id: id = " + id, e);
        }
    }

    public Film create(Film film) {
        try {
            return filmRepository.save(film);
        } catch (Exception e) {
            // log error
            throw new ServiceException("Erreur lors de la sauvegarde de film: " + film, e);
        }
    }
}
