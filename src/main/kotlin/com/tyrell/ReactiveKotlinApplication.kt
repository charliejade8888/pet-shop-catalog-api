package com.tyrell

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition(info = Info(title = "Pet Shop Catalogue API", version = "1.0",
description = "Documentation of the Pet Shop Catalogue API v1.0")
)
class ReactiveKotlinApplication

fun main(args: Array<String>) {
    runApplication<ReactiveKotlinApplication>(*args)
}
