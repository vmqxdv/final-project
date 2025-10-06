package org.lessons.java.spring_film.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_film.model.Film;
import org.lessons.java.spring_film.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmService {

  @Autowired
  private FilmRepository filmRepository;

  public List<Film> findAll() {
    return filmRepository.findAll();
  }

  public Film getById(Integer id) {
    Optional<Film> filmAttempt = filmRepository.findById(id);

    return filmAttempt.get();
  }

  public List<Film> findByName(String name) {
    return filmRepository.findByNameContainingIgnoreCase(name);
  }

  public Optional<Film> findById(Integer id) {
    return filmRepository.findById(id);
  }

  public Film create(Film film) {
    return filmRepository.save(film);
  }

  public Film update(Film film) {
    return filmRepository.save(film);
  }

  public void deleteById(Integer id) {
    filmRepository.deleteById(id);
  }

  public void delete(Film film) {
    deleteById(film.getId());
  }

  public Boolean existById(Integer id) {
    return filmRepository.existsById(id);
  }

  public Boolean exist(Film film) {
    return existById(film.getId());
  }
}
