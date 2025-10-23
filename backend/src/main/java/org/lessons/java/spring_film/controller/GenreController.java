package org.lessons.java.spring_film.controller;

import java.util.List;

import org.lessons.java.spring_film.model.Film;
import org.lessons.java.spring_film.model.Genre;
import org.lessons.java.spring_film.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/genres")
public class GenreController {

  @Autowired
  private GenreService genreService;

  @GetMapping
  public String index(@RequestParam(required = false) String name, Model model) {
    List<Genre> genres = name != null && !name.isBlank() ? genreService.findByName(name) : genreService.findAll();

    model.addAttribute("genres", genres);

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

    genreService.create(formGenre);

    return "redirect:/genres";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("genre", genreService.getById(id));

    return "genres/show";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    model.addAttribute("genre", genreService.findById(id).get());
    model.addAttribute("edit", true);

    return "genres/create-or-edit";
  }

  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute("genre") Genre formGenre, BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors())
      return "genres/create-or-edit";

    genreService.update(formGenre);

    return "redirect:/genres/" + formGenre.getId();
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id) {
    Genre genre = genreService.getById(id);

    for (Film film : genre.getFilms()) {
      film.getGenres().remove(genre);
    }

    genreService.delete(genre);

    return "redirect:/genres";
  }
}