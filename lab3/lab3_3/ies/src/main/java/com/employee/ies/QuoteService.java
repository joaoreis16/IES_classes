package com.employee.ies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {
    @Autowired
    private QuoteRepository repository;

    public Quote saveQuote(Quote movie) {
        return repository.save(movie);
    }

    public List<Quote> saveQuotes(List<Quote> movies) {
        return repository.saveAll(movies);
    }

    public List<Quote> getQuotes() {
        return repository.findAll();
    }

    public Quote getQuoteById(int id) {
        return repository.findById((int)id).orElse(null);
    }

    public Quote getQuoteByName(String title) {
        return repository.findByAvaliacao(title);
    }

    public String deleteQuote(int id) {
        repository.deleteById(id);
        return "product removed !! " + id;
    }

    public Quote updateQuote(Quote movie) {
        Quote existingMovie = repository.findById((int)movie.getId()).orElse(null);
        existingMovie.setAvaliacao(movie.getAvaliacao());
        return repository.save(existingMovie);
    }
}
