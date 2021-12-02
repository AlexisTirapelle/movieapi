package com.alexis.movieapi.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.alexis.movieapi.domain.Interval;
import com.alexis.movieapi.domain.Movie;
import com.alexis.movieapi.domain.Producer;
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
		return repository.findAll(Sort.by(Sort.Direction.ASC, "year"));

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

	public Interval findMinMaxInterval() {
		List<Movie> movies = findAll();
		List<Movie> winners = new ArrayList<Movie>();
		List<Producer> producers = new ArrayList<Producer>();
		List<Producer> producers2 = new ArrayList<Producer>();

		List<Producer> minList = new ArrayList<Producer>();
		List<Producer> maxList = new ArrayList<Producer>();
		Interval interval = new Interval();

		if (movies.size() > 0) {
			// gera uma lista apenas com os ganhadores
			for (Movie m : movies) {
				if (m.getWinner().equals("yes")) {
					winners.add(m);
				}
			}

			// gera uma lista com os produtores isolados
			for (Movie w : winners) {
				String[] nomes = w.getProducers().split(", and |, | and ");
				for (String n : nomes) {
					Producer producer = new Producer();
					producer.setName(n);
					producer.setPreviousWin(w.getYear());
					producer.setFollowingWin(w.getYear());
					producers.add(producer);
				}
			}

			// ordena lista (alfabeticamente) de produtores por nome
			if (producers.size() > 0) {
				Collections.sort(producers, new Comparator<Producer>() {
					@Override
					public int compare(final Producer object1, final Producer object2) {
						return object1.getName().compareTo(object2.getName());
					}
				});
			}

			for (int i = 1; i < producers.size(); i++) {
				if (producers.get(i).getName().equals(producers.get(i - 1).getName())) {
					Producer producer2 = new Producer();
					if (producers.get(i).getPreviousWin() > producers.get(i - 1).getPreviousWin()) {
						producer2.setFollowingWin(producers.get(i).getPreviousWin());
						producer2.setPreviousWin(producers.get(i - 1).getPreviousWin());
					} else {
						producer2.setFollowingWin(producers.get(i - 1).getPreviousWin());
						producer2.setPreviousWin(producers.get(i).getPreviousWin());
					}
					producer2.setName(producers.get(i).getName());
					producer2.setInterval(producer2.getFollowingWin() - producer2.getPreviousWin());
					producers2.add(producer2);
				}
			}

			// identifica o maior e menor intervalo
			int maior = producers2.get(0).getInterval();
			int menor = producers2.get(0).getInterval();
			for (Producer p : producers2) {
				if (p.getInterval() < menor)
					menor = p.getInterval();
				if (p.getInterval() > maior)
					maior = p.getInterval();
			}

			// gera listas com os Produtores com maior e menor intervalo
			for (Producer p : producers2) {
				if (p.getInterval() == menor)
					minList.add(p);
				if (p.getInterval() == maior)
					maxList.add(p);
			}

			// Preenche o objeto final com as listas de Maior e Menor para retorno da API
			interval.setMin(minList);
			interval.setMax(maxList);
		} else {
			interval = null;
		}

		return interval;
	}
}
