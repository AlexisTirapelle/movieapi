package com.alexis.movieapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexis.movieapi.domain.Movie;
import com.alexis.movieapi.repositories.MovieRepository;
import com.alexis.movieapi.services.exceptions.ObjectNotFoundException;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;

	public Movie findById(Integer id) {
		Optional<Movie> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Movie.class.getName()));
	}

	public List<Movie> findAll() {
		return repository.findAll();
	}

	public Movie update(Integer id, Movie obj) {
		Movie newObj = findById(id);
		newObj.setProducers(obj.getProducers());
		newObj.setStudios(obj.getStudios());
		newObj.setTitle(obj.getTitle());
		newObj.setWinner(obj.getWinner());
		newObj.setYear(obj.getYear());
		return repository.save(newObj);
	}

	public Movie create(Movie obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}
}
