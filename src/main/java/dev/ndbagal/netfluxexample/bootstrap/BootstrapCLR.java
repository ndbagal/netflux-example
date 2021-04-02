package dev.ndbagal.netfluxexample.bootstrap;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import dev.ndbagal.netfluxexample.domain.Movie;
import dev.ndbagal.netfluxexample.repositories.MovieRepository;
import reactor.core.publisher.Flux;

@Component
public class BootstrapCLR implements CommandLineRunner {

  private final MovieRepository movieRepository;

  @Override
  public void run(String... args) throws Exception {

    // Clear old data and populate new
    movieRepository.deleteAll()
        .thenMany(Flux
            .just("Silence of the Lambdas", "Aeon Flux", "Enter the Mono<Void>", "The Fluxxinator",
                "Back to the future", "Meet the fluxes", "Lord of the Fluxes")
            .map(title -> new Movie(title)).flatMap(movieRepository::save))
        .subscribe(null, null, () -> {
          movieRepository.findAll().subscribe(System.out::println);
        });

  }

  public BootstrapCLR(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

}
