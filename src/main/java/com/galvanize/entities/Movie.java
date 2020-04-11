package com.galvanize.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    @Column(name="imdb_id")
    private String imdbId;

    @Column(name="actors")
    //@ElementCollection(targetClass=String.class)
    private String actors;

    @Column(name= "director")
    //@ElementCollection(targetClass=String.class)
    private String director;

    @Column(name ="title")
    private String title;

    @Column(name="year")
    private String year;

    @Column(name="released")
    private Date releasedDate;

    @Column(name = "stars")
    private String stars;

    public Movie(){}


    public Movie(String imdbId, String actors, String director, String title, String year, Date releasedDate, String stars) {
        this.imdbId = imdbId;
        this.actors = actors;
        this.director = director;
        this.title = title;
        this.year = year;
        this.releasedDate = releasedDate;
        this.stars = stars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Date getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", imdbId='" + imdbId + '\'' +
                ", actors=" + actors +
                ", director=" + director +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", releasedDate=" + releasedDate +
                ", stars='" + stars + '\'' +
                '}';
    }

}
