package com.alexis.movieapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexis.movieapi.domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
