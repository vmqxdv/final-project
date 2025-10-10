package org.lessons.java.spring_film.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_film.model.Genre;
import org.lessons.java.spring_film.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/genres")
@CrossOrigin("http://localhost:5173/")
public class GenreRestController {

  @Autowired
  private GenreService genreService;

  @GetMapping
  public ResponseEntity<List<Genre>> index(@RequestParam(required = false) String name) {
    List<Genre> results;

    if (name != null && !name.isBlank())
      results = genreService.findByName(name);
    else
      results = genreService.findAll();

    if (results.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    return new ResponseEntity<>(results, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Genre> show(@PathVariable Integer id) {
    Optional<Genre> genreAttempt = genreService.findById(id);

    if (genreAttempt.isEmpty())
      return new ResponseEntity<Genre>(HttpStatus.NOT_FOUND);

    return new ResponseEntity<Genre>(genreAttempt.get(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Genre> store(@Valid @RequestBody Genre genre) {
    return new ResponseEntity<Genre>(genreService.create(genre), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Genre> update(@Valid @RequestBody Genre genre, @PathVariable Integer id) {
    if (genreService.findById(id).isEmpty())
      return new ResponseEntity<Genre>(HttpStatus.NOT_FOUND);

    genre.setId(id);

    return new ResponseEntity<Genre>(genreService.update(genre), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@Valid @PathVariable Integer id) {
    if (genreService.findById(id).isEmpty())
      return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

    genreService.deleteById(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

}
