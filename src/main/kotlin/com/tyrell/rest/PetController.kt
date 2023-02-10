package com.tyrell.rest

import com.tyrell.models.Pet
import com.tyrell.requests.PetCreateRequest
import com.tyrell.requests.PetUpdateRequest
import com.tyrell.responses.PetCreateResponse
import com.tyrell.responses.PetUpdateResponse
import com.tyrell.serivce.CatalogueService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/catalog", produces = [MediaType.APPLICATION_JSON_VALUE])
internal class PetController(
    val catalogueService: CatalogueService
) {
    @Operation(operationId = "createPet", summary = "Create pet")
    @PostMapping("/add")
    suspend fun createPet(
        @RequestBody request: PetCreateRequest
    ): PetCreateResponse {
        return catalogueService.createPet(request)
    }

    @GetMapping(value = ["/search"])
    @Operation(operationId = "findPetByName", summary = "Find a pet by name")
    suspend fun searchPetByName(
        @RequestParam
        petName: String,
    ): ResponseEntity<Pet>? {
        return catalogueService.findPet(petName)
    }

    @Operation(operationId = "listPets", summary = "List pets")
    @GetMapping("/list")
    suspend fun listPets(
        @RequestParam pageNo: Int = 1,
        @RequestParam pageSize: Int = 10
    ): ResponseEntity<List<Pet>> {
        return catalogueService.listPets(pageNo = pageNo, pageSize = pageSize)
    }

    @Operation(operationId = "updatePet", summary = "Update Pet")
    @PatchMapping("/update/{name}")
    suspend fun updatePet(
        @PathVariable name: String,
        @RequestBody petUpdateRequest: PetUpdateRequest
    ): PetUpdateResponse {
        return catalogueService.updatePet(name = name, petUpdateRequest = petUpdateRequest)
    }

    @Operation(operationId = "deletePet", summary = "Delete pet")
    @DeleteMapping("/delete/{name}")
    suspend fun deleteUser(
        @PathVariable name: String
    ) {
        catalogueService.deletePet(name = name)
    }

}
