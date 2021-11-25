package com.alexis.movieapi;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alexis.movieapi.domain.Movie;
import com.alexis.movieapi.repositories.MovieRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@SpringBootApplication
public class MovieapiApplication implements CommandLineRunner {

	@Autowired
	private MovieRepository movieRepository;

	public static void main(String[] args) {
		SpringApplication.run(MovieapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		Movie m = new Movie(null, "year", "title", "studios", "producers", "winner");
//		Movie m2 = new Movie(null, "year2", "title2", "studios2", "producers2", "winner2");
//
//		movieRepository.saveAll(Arrays.asList(m, m2));

		// TODO criar classe de arquivos CSV 
		Reader reader;
		try {
			reader = Files.newBufferedReader(Paths.get("movielist.csv"));

			CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

			CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).withSkipLines(1).build();

			List<String[]> csv_movies = csvReader.readAll();

			Movie movie;

			for (String[] row : csv_movies) {
				movie = new Movie(null, row[0], row[1], row[2], row[3], row[4]);
				movieRepository.saveAll(Arrays.asList(movie));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
