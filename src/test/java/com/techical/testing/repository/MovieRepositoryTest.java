package com.techical.testing.repository;

import com.techical.testing.entity.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    private Movie avatarMovie;
    private Movie titanicMovie;

    @BeforeEach
    void init() {
        avatarMovie = new Movie();
        avatarMovie.setName("Avatar");
        avatarMovie.setGenera("Action");
        avatarMovie.setReleaseDate(LocalDate.of(2014, Month.APRIL, 22));

        titanicMovie = new Movie();
        titanicMovie.setName("Titanic");
        titanicMovie.setGenera("Romance");
        titanicMovie.setReleaseDate(LocalDate.of(1999, Month.MAY, 20));
    }

    @Test
    @DisplayName("It should return new movie after save")
    void saveMovie() {
        Movie newMovie = movieRepository.save(avatarMovie);

        assertNotNull(newMovie);
    }

    @Test
    void getAllMovies() {
        movieRepository.save(avatarMovie);
        movieRepository.save(titanicMovie);

        List<Movie> movieList = movieRepository.findAll();
        assertNotNull(movieList);
    }

    @Test
    void getMovieById() {
        movieRepository.save(avatarMovie);
        Movie newMovie = movieRepository.findById(avatarMovie.getId()).get();

        assertNotNull(newMovie);
    }

    @Test
    void updateMovie() {
        movieRepository.save(avatarMovie);

        Movie existingMovie = movieRepository.findById(avatarMovie.getId()).get();
        existingMovie.setReleaseDate(LocalDate.now());
        Movie updateMovie = movieRepository.save(existingMovie);
        assertEquals(existingMovie.getReleaseDate(), updateMovie.getReleaseDate());
    }

    @Test
    void deleteMovie() {
        movieRepository.save(avatarMovie);

        movieRepository.save(titanicMovie);

        movieRepository.delete(avatarMovie);

        List<Movie> movieListAfter = movieRepository.findAll();

        assertEquals(movieListAfter.size(), 2);
    }

    @Test
    void findByGenera() {
        movieRepository.save(avatarMovie);

        List<Movie> actionMovie = movieRepository.findByGenera("Action");
        assertNotNull(actionMovie);
    }
}