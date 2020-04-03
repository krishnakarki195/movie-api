package com.galvanize.controllers;

import com.galvanize.entities.Movie;
import com.galvanize.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping(value = "/api/movies")
    public List<Movie> getAllMovies(){
        return movieService.getAll();
    }

    @PostMapping(value = "/api/movies")
    public Movie save(Movie movie){
        return movieService.save(movie);
    }

    @GetMapping(value = "/api/movies/{id}")
    public Movie getById(@PathVariable("id") Long id){
        return movieService.getById(id).get();
    }

    @DeleteMapping("/api/movies/{id}")
    public boolean deleteById(@PathVariable("id") Long id){
        return movieService.deleteById(id);
    }

//    @PatchMapping("/api/movies/{id}")
//    public Movie updateMovieTitleById(@PathVariable("id") Long id, @RequestParam("title") String title){
//        return movieService.updateMovieTitle(id,title);
//    }

    @PatchMapping("/api/movies/{id}")
    public Movie updateMovieRatingById(@PathVariable("id") Long id, @RequestParam("stars") String stars){
        return movieService.updateMovieRating(id,stars);
    }

}
