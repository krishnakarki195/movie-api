package com.galvanize.entities;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    public void movieObjDefaultConstructorTest(){
        Movie movie = new Movie();
        assertNotNull(movie);
    }

    @Test
    public void movieObjParameterConstructorTest(){
        String imdb_id = "imdb_123";
        List<String> actors = new ArrayList<>();
        List<String> director = new ArrayList<>();
        String title = "Movie Title";
        String year = "2016";
        Date releasedDate = Date.valueOf(LocalDate.of(1962, 11, 16));
        Movie movie = new Movie(imdb_id,actors,director,title,year,releasedDate);
        assertNotNull(movie);
        assertEquals("2016", movie.getYear());
    }

}