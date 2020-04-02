package com.galvanize.services;

import com.galvanize.entities.Movie;
import com.galvanize.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class MovieServiceTest {

    @Autowired
    MovieRepository movieRepository;

    @BeforeEach
    public void setupTestData(){

        List<String> m1_actors = Arrays.asList("Dean Cain", "Robin Givens", "Tamara Goodwin", "Matt Mercer");
        List<String> m1_director = Arrays.asList("James Kondelik", "Jon Kondelik");
        Movie m1 = new Movie("tt3417334",
                m1_actors,
                m1_director,
                "Airplane vs. Volcano",
                "2014",
                Date.valueOf(LocalDate.of(2014, 03, 28)));

        List<String> m2_actors = Arrays.asList("Kareem Abdul-Jabbar", "Lloyd Bridges", "Peter Graves", "Julie Hagerty");
        List<String> m2_director = Arrays.asList("Jim Abrahams", "David Zucker", "Jerry Zucker");
        Movie m2 = new Movie("tt0083530",
                m2_actors,
                m2_director,
                "Airplane!",
                "1980",
                Date.valueOf(LocalDate.of(1980, 07, 02)));

        List<String> m3_actors = Arrays.asList("William Shatner", "Leonard Nimoy", "DeForest Kelley", "James Doohan");
        List<String> m3_director = Arrays.asList("JLeonard Nimoy");
        Movie m3 = new Movie("tt0080339",
                m3_actors,
                m3_director,
                "Star Trek III: The Search for Spock",
                "1984",
                Date.valueOf(LocalDate.of(1984, 06, 01)));
    }

    @Test
    public void getAllMovieTest(){

    }

}