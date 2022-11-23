package com.techical.testing.services;

import com.techical.testing.entity.Movie;
import com.techical.testing.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    void save() {
        Movie avatarMovie = new Movie();
        avatarMovie.setId(1L);
        avatarMovie.setName("Avatar");
        avatarMovie.setGenera("Action");


        when(movieRepository.save(any(Movie.class))).thenReturn(avatarMovie);
        Movie movie = movieService.save(avatarMovie);
        assertThat(avatarMovie.getName().equals(movie.getName()));
    }

    @Test
    void getAllMovies() {
        Movie avatarMovie = new Movie();
        avatarMovie.setId(1L);
        avatarMovie.setName("Avatar");
        avatarMovie.setGenera("Action");
        avatarMovie.setReleaseDate(LocalDate.of(2012,Month.APRIL, 22));

        Movie titanicMovie = new Movie();
        avatarMovie.setId(2L);
        avatarMovie.setName("Titanic");
        avatarMovie.setGenera("Romance");
        avatarMovie.setReleaseDate(LocalDate.of(2014,Month.MAY, 12));

        List<Movie> movieList = new ArrayList<>();
        movieList.add(avatarMovie);
        movieList.add(titanicMovie);

        when(movieRepository.findAll()).thenReturn(movieList);
        List<Movie> allMovies = movieService.getAllMovies();
        assertEquals(movieList.size(), allMovies.size());
    }

    @Test
    void getMovieById() {
        Movie avatarMovie = new Movie();
        avatarMovie.setId(1L);
        avatarMovie.setName("Avatar");
        avatarMovie.setGenera("Action");
        avatarMovie.setReleaseDate(LocalDate.of(2012,Month.APRIL, 22));

        when(movieRepository.findById(1L)).thenReturn(Optional.of(avatarMovie));
        Movie movieById = movieService.getMovieById(1L);
        assertEquals(avatarMovie.getId(), movieById.getId());
    }

    @Test
    void updateMovie() {
        Movie avatarMovie = new Movie();
        avatarMovie.setId(1L);
        avatarMovie.setName("Avatar");
        avatarMovie.setGenera("Action");
        avatarMovie.setReleaseDate(LocalDate.of(2012,Month.APRIL, 22));

        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(avatarMovie));

        Movie existMovie = movieService.getMovieById(1L);
        existMovie.setReleaseDate(LocalDate.now());

        when(movieRepository.save(any(Movie.class))).thenReturn(avatarMovie);

    }

    @Test
    void deleteMovie() {
        Movie avatarMovie = new Movie();
        avatarMovie.setId(1L);
        avatarMovie.setName("Avatar");
        avatarMovie.setGenera("Action");
        avatarMovie.setReleaseDate(LocalDate.of(2012,Month.APRIL, 22));

        when(movieRepository.save(any(Movie.class))).thenReturn(avatarMovie);

    }
}