package com.galvanize.services;

import com.galvanize.entities.Movie;
import com.galvanize.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if(movie.isPresent())
            return movie;
        else
            throw new RuntimeException("Movie not found");
    }

    public Movie updateMovieRating(Long id, String stars) {
        if(movieRepository.findById(id).isPresent()){
            Movie movie = movieRepository.findById(id).get();
            movie.setStars(stars);
            return movie;
        }else{
            throw new RuntimeException("Movie not found");
        }
    }

    public Boolean deleteById(Long id) {
        if(movieRepository.findById(id).isPresent()){
            movieRepository.deleteById(id);
            return !movieRepository.existsById(id);
        }else{
            throw new RuntimeException("Movie not found");
        }
    }

}
