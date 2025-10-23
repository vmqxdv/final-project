package org.lessons.java.spring_film.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_film.model.Film;
import org.lessons.java.spring_film.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/films")
@CrossOrigin("http://localhost:5173/")
public class FilmRestController {

  @Autowired
  private FilmService filmService;

  @GetMapping
  public ResponseEntity<List<Film>> index(@RequestParam(required = false) String name) {
    List<Film> results;

    if (name != null && !name.isBlank())
      results = filmService.findByName(name);
    else
      results = filmService.findAll();

    if (results.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    return new ResponseEntity<>(results, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Film> show(@PathVariable Integer id) {
    Optional<Film> filmAttempt = filmService.findById(id);

    if (filmAttempt.isEmpty())
      return new ResponseEntity<Film>(HttpStatus.NOT_FOUND);

    return new ResponseEntity<Film>(filmAttempt.get(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Film> store(@Valid @RequestBody Film film) {
    return new ResponseEntity<Film>(filmService.create(film), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Film> update(@Valid @RequestBody Film film, @PathVariable Integer id) {
    if (filmService.findById(id).isEmpty())
      return new ResponseEntity<Film>(HttpStatus.NOT_FOUND);

    film.setId(id);

    return new ResponseEntity<Film>(filmService.update(film), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    if (filmService.findById(id).isEmpty())
      return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

    filmService.deleteById(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }
}
