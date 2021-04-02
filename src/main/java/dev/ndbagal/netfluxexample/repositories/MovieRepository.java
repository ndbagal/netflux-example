package dev.ndbagal.netfluxexample.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import dev.ndbagal.netfluxexample.domain.Movie;

public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {

}
