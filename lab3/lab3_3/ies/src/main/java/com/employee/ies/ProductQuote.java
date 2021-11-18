package com.employee.ies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQuote {
    @Autowired
    private MovieRepository repository;

    public Movie saveMovie(Movie movie) {
        return repository.save(movie);
    }

    public List<Movie> saveMovies(List<Movie> movies) {
        return repository.saveAll(movies);
    }
    /*
    public List<Quote> getQuotes() {
        return repository.find
    }
    */

    public List<Movie> getMovies() {
        return repository.findAll();
    }

    public Movie getMovieById(long id) {
        return repository.findById((int)id).orElse(null);
    }

    public Movie getMovieByName(String title) {
        return repository.findByTitle(title);
    }

    public String deleteMovie(int id) {
        repository.deleteById(id);
        return "product removed !! " + id;
    }

    public Movie updateMovie(Movie movie) {
        Movie existingMovie = repository.findById((int)movie.getId()).orElse(null);
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setYear(movie.getYear());
        return repository.save(existingMovie);
    }
}