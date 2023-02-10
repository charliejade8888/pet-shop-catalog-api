package com.tyrell.requests

import com.tyrell.models.Pet

internal data class PetCreateRequest(
    val name: String,
    val description: String,
    val type: String,
    val price: String,
    val breed: String)
internal fun PetCreateRequest.toPet(): Pet {
    return Pet(
        name = name,
        description = description,
        type = type,
        price = price,
        breed = breed
    )
}

