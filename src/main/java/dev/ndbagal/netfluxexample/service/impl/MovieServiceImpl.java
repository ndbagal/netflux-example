package dev.ndbagal.netfluxexample.service.impl;

import java.time.Duration;
import java.util.Date;

import org.springframework.stereotype.Service;

import dev.ndbagal.netfluxexample.domain.Movie;
import dev.ndbagal.netfluxexample.domain.MovieEvent;
import dev.ndbagal.netfluxexample.repositories.MovieRepository;
import dev.ndbagal.netfluxexample.service.MovieService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieServiceImpl implements MovieService {

  private final MovieRepository movieRepository;

  @Override
  public Flux<MovieEvent> events(String movieId) {
    return Flux.<MovieEvent>generate(movieEventSynchronousSink -> {
      movieEventSynchronousSink.next(new MovieEvent(movieId, new Date()));
    }).delayElements(Duration.ofSeconds(1));
  }

  @Override
  public Mono<Movie> getMovieById(String id) {
    return movieRepository.findById(id);
  }

  @Override
  public Flux<Movie> getAllMovies() {
    return movieRepository.findAll();
  }

  public MovieServiceImpl(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

}
