package org.lessons.java.spring_film.repository;

import java.util.Optional;

import org.lessons.java.spring_film.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByUsername(String username);
}
