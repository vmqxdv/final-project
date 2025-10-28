package org.lessons.java.spring_film.controller;

import java.util.List;

import org.lessons.java.spring_film.model.Film;
import org.lessons.java.spring_film.service.FilmService;
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
@RequestMapping("/films")
public class FilmController {

  @Autowired
  private FilmService filmService;

  @Autowired
  private GenreService genreService;

  @GetMapping
  public String index(@RequestParam(required = false) String name, Model model) {
    List<Film> films = name != null && !name.isBlank() ? filmService.findByName(name) : filmService.findAll();

    model.addAttribute("films", films);

    return "films/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("film", filmService.getById(id));

    return "films/show";
  }

  @GetMapping("/create")
  public String create(Model model) {
    model.addAttribute("film", new Film());
    model.addAttribute("genres", genreService.findAll());
    model.addAttribute("edit", false);

    return "films/create-or-edit";
  }

  @PostMapping("/create")
  public String store(@Valid @ModelAttribute("film") Film formFilm,
      BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("genres", genreService.findAll());
      model.addAttribute("edit", false);

      return "films/create-or-edit";
    }

    filmService.create(formFilm);

    return "redirect:/films";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    Film film = filmService.getById(id);

    model.addAttribute("film", film);
    model.addAttribute("genres", genreService.findAll());
    model.addAttribute("edit", true);

    return "films/create-or-edit";
  }

  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute("film") Film formFilm,
      BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("genres", genreService.findAll());
      model.addAttribute("edit", true);

      return "films/create-or-edit";
    }

    filmService.update(formFilm);

    return "redirect:/films/" + formFilm.getId();
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id) {
    filmService.deleteById(id);

    return "redirect:/films";
  }

}
