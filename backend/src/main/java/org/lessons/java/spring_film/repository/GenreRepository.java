package org.lessons.java.spring_film.repository;

import java.util.List;

import org.lessons.java.spring_film.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
  List<Genre> findByNameContainingIgnoreCase(String name);
}
