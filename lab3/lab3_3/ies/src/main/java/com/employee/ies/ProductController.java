package com.employee.ies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductQuote service;


    @PostMapping("/addMovie")
    public Movie addMovie(@RequestBody Movie m) {
        return service.saveMovie(m);
    }

   

    @GetMapping("/shows")
    public List<Movie> findAllMovies() {
        return service.getMovies();
    }

   

    

    @PutMapping("/update")
    public Movie updateProduct(@RequestBody Movie product) {
        return service.updateMovie(product);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMovie(@PathVariable int id) {
        return service.deleteMovie(id);
    }
}
