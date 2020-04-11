package com.galvanize.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.entities.Movie;
import com.galvanize.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class MovieControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    MovieService movieService;

    @Autowired
    ObjectMapper objectMapper;

    Movie movie;

    @BeforeEach
    public void setupTestData(){
        String m1_actors = "Dean Cain, Robin Givens, Tamara Goodwin, Matt Mercer";
        String m1_director = "James Kondelik, Jon Kondelik";
        movie = new Movie("tt3417334",
                m1_actors,
                m1_director,
                "Airplane vs. Volcano",
                "2014",
                Date.valueOf(LocalDate.of(2014, 03, 28)), "5");
        movie.setId(1L);
    }

    @Test
    public void saveMovieApiTest() throws Exception{

        String url = "/api/movies";

        when(movieService.save(ArgumentMatchers.any(Movie.class))).thenReturn(movie);

        mvc.perform(post(url).content(objectMapper.writeValueAsString(movie))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(movie.getId()))
                .andExpect(jsonPath("$.year").value(movie.getYear()))
                .andDo(print());
    }

    @Test
    public void findAllMovieApiTest() throws Exception {
        String url = "/api/movies";

        when(movieService.getAll()).thenReturn(Arrays.asList(movie));

        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andDo(print());
    }

    @Test
    public void findByImdbIdMovieApiTest() throws Exception {
        String url = "/api/movies/imdbid/tt3417334";

        when(movieService.getByImdbid(ArgumentMatchers.any(String.class))).thenReturn(movie);

        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(movie.getId()))
                .andExpect((jsonPath("$.title").value(movie.getTitle())))
                .andDo(print());
    }

    @Test
    public void findByIdMovieApiTest() throws Exception {
        String url = "/api/movies/1";

        when(movieService.getById(ArgumentMatchers.any(Long.class))).thenReturn(java.util.Optional.ofNullable(movie));

        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(movie.getId()))
                .andExpect((jsonPath("$.title").value(movie.getTitle())))
                .andDo(print());
    }

    @Test
    public void deleteByIdMovieApiTest() throws Exception {
        String url = "/api/movies/1";

        when(movieService.deleteById(ArgumentMatchers.any(Long.class))).thenReturn(Boolean.valueOf("true"));

        mvc.perform(delete(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void updateMovieRatingByIdApiTest() throws Exception {
        String url = "/api/movies/1";
        String stars = "5";

        when(movieService.updateMovieRating(ArgumentMatchers.any(Long.class),ArgumentMatchers.any(String.class))).thenReturn(movie);

        mvc.perform(patch(url)
                .contentType(MediaType.APPLICATION_JSON)
                .param("stars",stars))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stars").value(movie.getStars()))
                .andExpect(jsonPath("$.year").value(movie.getYear()))
                .andDo(print());
    }


}