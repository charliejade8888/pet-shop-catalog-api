package com.tyrell.models

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.tyrell.responses.PetCreateResponse
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@JsonSerialize
@Table("pets")
data class Pet(
    @Id
    var id: Int? = null,
    @NotNull
    val name: String,
    var description: String,
    val type: String,
    var price: String,
    val breed: String)
internal fun Pet.toPetCreateResponse(): PetCreateResponse = PetCreateResponse(
    id = id,
    name = name,
    description = description,
    type = type,
    price = price,
    breed = breed
)
