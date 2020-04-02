package com.galvanize.services;

import com.galvanize.entities.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class MovieServiceTest {

    @Autowired
    MovieService movieService;

    Movie m1, m2, m3;

    @BeforeEach
    public void setupTestData(){

        List<String> m1_actors = Arrays.asList("Dean Cain", "Robin Givens", "Tamara Goodwin", "Matt Mercer");
        List<String> m1_director = Arrays.asList("James Kondelik", "Jon Kondelik");
        m1 = new Movie("tt3417334",
                m1_actors,
                m1_director,
                "Airplane vs. Volcano",
                "2014",
                Date.valueOf(LocalDate.of(2014, 03, 28)), "5");

        List<String> m2_actors = Arrays.asList("Kareem Abdul-Jabbar", "Lloyd Bridges", "Peter Graves", "Julie Hagerty");
        List<String> m2_director = Arrays.asList("Jim Abrahams", "David Zucker", "Jerry Zucker");
        m2 = new Movie("tt0083530",
                m2_actors,
                m2_director,
                "Airplane!",
                "1980",
                Date.valueOf(LocalDate.of(1980, 07, 02)), "3");

        List<String> m3_actors = Arrays.asList("William Shatner", "Leonard Nimoy", "DeForest Kelley", "James Doohan");
        List<String> m3_director = Arrays.asList("JLeonard Nimoy");
        m3 = new Movie("tt0080339",
                m3_actors,
                m3_director,
                "Star Trek III: The Search for Spock",
                "1984",
                Date.valueOf(LocalDate.of(1984, 06, 01)), "4");
    }

    @Test
    public void saveMovieTest(){

        Movie expected = movieService.save(m1);

        assertEquals(m1.getActors().size(), expected.getActors().size());
        assertEquals(m1.getReleasedDate(), expected.getReleasedDate());
        assertEquals(expected.getImdbId(), m1.getImdbId());
    }

    @Test
    public void getAllMovieTest(){

        List<Movie> expected = new ArrayList<>();
        expected.addAll(Arrays.asList(m1,m2,m3));
        expected.forEach((movie -> movieService.save(movie)));

        assertEquals(expected.size(), movieService.getAll().size());

    }

    @Test
    public void getByIdMovieTest(){

        Movie expected = movieService.save(m1);
        Movie actual = movieService.getById(expected.getId()).get();

        assertEquals(expected.getImdbId(), actual.getImdbId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertThrows(RuntimeException.class, ()-> movieService.getById(1000L));

    }

    @Test
    public void updateMovieTitleTest(){

        String updateTitle = "New title updated";
        Movie oldMovie = movieService.save(m1);
        Movie expected = movieService.updateMovieTitle(oldMovie.getId(), updateTitle);

        assertEquals(updateTitle, expected.getTitle());
        assertThrows(RuntimeException.class, ()-> movieService.updateMovieTitle(1000L, updateTitle));

    }

    @Test
    public void deleteByIdMovieTest(){

        Movie movie = movieService.save(m1);
        Boolean actual = movieService.deleteById(movie.getId());

        assertEquals(true, actual);
        assertThrows(RuntimeException.class, ()-> movieService.deleteById(1000L));

    }

}