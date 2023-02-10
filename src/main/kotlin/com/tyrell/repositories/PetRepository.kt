package com.tyrell.repositories

import com.tyrell.models.Pet
import com.tyrell.models.User
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@EnableR2dbcRepositories
interface PetRepository : R2dbcRepository<Pet, Int> {
    fun findByName(name: String): Mono<Pet>

    @Query("SELECT * FROM pets limit :limit offset :offset")
    fun findAllPets(limit: Int, offset: Int): Flux<Pet>

    fun deleteByName(name: String)
}
