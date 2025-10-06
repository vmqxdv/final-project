package org.lessons.java.spring_film.repository;

import java.util.List;

import org.lessons.java.spring_film.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {
  List<Film> findByNameContainingIgnoreCase(String name);
}
