package com.tyrell.responses

internal data class PetCreateResponse(
    var id: Int?,
    val name: String,
    val description: String,
    val type: String,
    val price: String,
    val breed: String
)

