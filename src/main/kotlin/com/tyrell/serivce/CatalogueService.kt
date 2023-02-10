package com.tyrell.serivce

import com.tyrell.exceptions.DuplicatePetException
import com.tyrell.exceptions.PetNotFoundException
import com.tyrell.models.Pet
import com.tyrell.models.toPetCreateResponse
import com.tyrell.repositories.PetRepository
import com.tyrell.requests.PetCreateRequest
import com.tyrell.requests.PetUpdateRequest
import com.tyrell.requests.toPet
import com.tyrell.responses.PetCreateResponse
import com.tyrell.responses.PetUpdateResponse
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrElse
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.apache.commons.lang3.StringUtils.isNotBlank
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
internal class CatalogueService(
    private val petRepository: PetRepository
) {

    suspend fun createPet(request: PetCreateRequest): PetCreateResponse {
        val existingPet = petRepository.findByName(request.name).awaitFirstOrNull()
        if (existingPet != null) {
            throw DuplicatePetException("Duplicate pet")
        }
        val createdPet = petRepository.save(request.toPet()).awaitFirstOrNull()
        return createdPet!!.toPetCreateResponse()
    }

    suspend fun findPet(petName: String): ResponseEntity<Pet> {
        val pet = petRepository.findByName(petName).awaitFirstOrNull()
            ?: throw PetNotFoundException("No pet found")
        return ResponseEntity.ok(pet)
    }

    suspend fun listPets(pageNo: Int, pageSize: Int): ResponseEntity<List<Pet>> {
        val limit = pageSize
        val offset = (limit * pageNo) - limit
        val list = petRepository.findAllPets(limit, offset).collectList().awaitFirst()
        return ResponseEntity.ok(list)
    }

    suspend fun updatePet(name: String, petUpdateRequest: PetUpdateRequest): PetUpdateResponse {
        val pet = petRepository.findByName(name).awaitFirstOrElse {
            throw PetNotFoundException("Pet $name doesn't exist")
        }
        pet.price = petUpdateRequest.price?: pet.price
        val updatedPet = petRepository.save(pet).awaitFirst()
        return PetUpdateResponse(
            name = updatedPet.name,
            price = updatedPet.price
        )
    }

    suspend fun deletePet(name: String) {
        val existingPet = petRepository.findByName(name).awaitFirstOrElse {
            throw PetNotFoundException("Pet $name not found")
        }
        // subscribe is async, we know it exists, so we can safely now delete async for speed!
        // block on the other hand is synchronous (blocking)
        petRepository.delete(existingPet).subscribe()
    }

    companion object {
        @Suppress("UnusedPrivateMember")
        private val log = LoggerFactory.getLogger(this::class.java)
    }
}
