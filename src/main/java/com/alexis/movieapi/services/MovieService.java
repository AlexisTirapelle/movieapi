package com.alexis.movieapi.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	public Interval findMinMaxInterval() {
		List<Movie> movies = findAll();
		List<Movie> winners = new ArrayList<Movie>();
		List<Producer> producers = new ArrayList<Producer>();
		List<Producer> producersWithInterval = new ArrayList<Producer>();
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

			/*
			 * Valida se o objeto anterior tem o mesmo nome e atualiza os campos previousWin
			 * e followingWin de acordo com o maior e o menor entre os objetos de mesmo nome
			 * após isto é removido da lista o objeto anterior para que fique apenas um com
			 * os dados corretos
			 */
			for (int i = 1; i < producers.size(); i++) {
				if (producers.get(i).getName().equals(producers.get(i - 1).getName())) {
					if (producers.get(i).getPreviousWin() > producers.get(i - 1).getPreviousWin()) {
						producers.get(i).setPreviousWin(producers.get(i - 1).getPreviousWin());
					}
					if (producers.get(i).getFollowingWin() < producers.get(i - 1).getFollowingWin()) {
						producers.get(i).setFollowingWin(producers.get(i - 1).getFollowingWin());
					}
					producers.remove(producers.get(i - 1));
					// como há a remoção de um objeto da lista, é necessário decrementar o índice em
					// 1 pra que o loop funcione corretamente
					i--;
				}
			}

			// calcula e seta o intervalo
			for (Producer p : producers) {
				int intervalo = p.getFollowingWin() - p.getPreviousWin();
				p.setInterval(intervalo);
			}

			// gera nova lista apenas com os objetos que contêm intervalo
			for (Producer p : producers) {
				if (p.getInterval() != 0) {
					producersWithInterval.add(p);
				}
			}

			// identifica o maior e menor intervalo
			int maior = producersWithInterval.get(0).getInterval();
			int menor = producersWithInterval.get(0).getInterval();
			for (Producer p : producersWithInterval) {
				if (p.getInterval() < menor)
					menor = p.getInterval();
				if (p.getInterval() > maior)
					maior = p.getInterval();
			}

			// gera listas com os Produtores com maior e menor intervalo
			for (Producer p : producersWithInterval) {
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
