package com.tyrell.repositories

import com.tyrell.models.Pet
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@EnableR2dbcRepositories
interface PetRepository : R2dbcRepository<Pet, Int> {

    // Mono is reactive container for single object, similar to a Java Optional
    // Flux is reactive container for multiple objects, similar to a Java List
    // see - https://www.baeldung.com/java-reactor-flux-vs-mono
    fun findByName(name: String): Mono<Pet>

    @Query("SELECT * FROM pets limit :limit offset :offset")
    fun findAllPets(limit: Int, offset: Int): Flux<Pet>

    fun deleteByName(name: String)
}
