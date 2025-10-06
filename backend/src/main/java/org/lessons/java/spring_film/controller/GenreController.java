package org.lessons.java.spring_film.controller;

import java.util.Optional;

import org.lessons.java.spring_film.model.Film;
import org.lessons.java.spring_film.model.Genre;
import org.lessons.java.spring_film.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/genres")
public class GenreController {

  @Autowired
  private GenreRepository genreRepository;

  @GetMapping
  public String index(Model model) {
    model.addAttribute("genres", genreRepository.findAll());

    return "genres/index";
  }

  @GetMapping("/create")
  public String create(Model model) {
    model.addAttribute("genre", new Genre());

    return "genres/create-or-edit";
  }

  @PostMapping("/create")
  public String store(@Valid @ModelAttribute("genre") Genre formGenre, BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors())
      return "genres/create";

    genreRepository.save(formGenre);

    return "redirect:/genres";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable("id") Integer id, Model model) {
    Optional<Genre> genre = genreRepository.findById(id);
    model.addAttribute("genre", genre.orElse(null));
    return "genres/show";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    model.addAttribute("genre", genreRepository.findById(id).get());
    model.addAttribute("edit", true);

    return "genres/create-or-edit";
  }

  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute("film") Genre formGenre, BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors())
      return "genres/create-or-edit";

    genreRepository.save(formGenre);

    return "redirect:/genres/" + formGenre.getId();
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id) {
    Genre genre = genreRepository.findById(id).get();

    for (Film pizza : genre.getFilms()) {
      pizza.getGenres().remove(genre);
    }

    genreRepository.delete(genre);

    return "redirect:/genres";
  }
}