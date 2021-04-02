package dev.ndbagal.netfluxexample.service;

import dev.ndbagal.netfluxexample.domain.Movie;
import dev.ndbagal.netfluxexample.domain.MovieEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {

  /**
   * Generate Stream of movie events for given movie id
   * 
   * @param movieId
   * @return
   */
  Flux<MovieEvent> events(String movieId);

  /**
   * Get movie by id
   * 
   * @param id
   * @return
   */
  Mono<Movie> getMovieById(String id);

  /**
   * Return list of all movies
   * 
   * @return
   */
  Flux<Movie> getAllMovies();
}
