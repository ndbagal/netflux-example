package dev.ndbagal.netfluxexample.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ndbagal.netfluxexample.domain.Movie;
import dev.ndbagal.netfluxexample.domain.MovieEvent;
import dev.ndbagal.netfluxexample.service.MovieService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
public class MovieController {

  private final MovieService movieService;

  @GetMapping(value = "/{movieId}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  Flux<MovieEvent> streamMovieEvents(@PathVariable String movieId) {
    return movieService.events(movieId);
  }

  @GetMapping("/{id}")
  Mono<Movie> getMovieById(@PathVariable String id) {
    return movieService.getMovieById(id);
  }

  @GetMapping
  Flux<Movie> getAllMovies() {
    return movieService.getAllMovies();
  }

  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

}
