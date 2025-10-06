package org.lessons.java.spring_film.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_film.model.Genre;
import org.lessons.java.spring_film.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

  @Autowired
  private GenreRepository genreRepository;

  public List<Genre> findAll() {
    return genreRepository.findAll();
  }

  public Genre getById(Integer id) {
    Optional<Genre> genre = genreRepository.findById(id);

    if (genre.isEmpty()) {
    }

    return genre.get();
  }

  public List<Genre> findByName(String name) {
    return genreRepository.findByNameContainingIgnoreCase(name);
  }

  public Optional<Genre> findById(Integer id) {
    return genreRepository.findById(id);
  }

  public Genre create(Genre genre) {
    return genreRepository.save(genre);
  }

  public Genre update(Genre genre) {
    return genreRepository.save(genre);
  }

  public void deleteById(Integer id) {
    genreRepository.deleteById(id);
  }

  public void delete(Genre genre) {
    deleteById(genre.getId());
  }

  public Boolean existById(Integer id) {
    return genreRepository.existsById(id);
  }

  public Boolean exist(Genre genre) {
    return existById(genre.getId());
  }
}
