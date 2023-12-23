package com.tyrell.requests

import com.tyrell.models.Pet

internal data class PetCreateRequest(
    // for how to use validation annnotations this along with @Valid see -
    // https://medium.com/@himani.prasad016/validations-in-spring-boot-e9948aa6286b#:~:text=13.,that%20it%20should%20be%20validated.
    val name: String,
    val description: String,
    val type: String,
    val price: String,
    val breed: String)
internal fun PetCreateRequest.toPet(): Pet { // extension function useful for to/from factories here)
    return Pet(
        name = name,
        description = description,
        type = type,
        price = price,
        breed = breed
    )
}

