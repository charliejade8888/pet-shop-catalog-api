package com.tyrell.config

import com.tyrell.exceptions.DuplicatePetException
import com.tyrell.exceptions.PetNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class PetShopControllerAdvice {
    @ExceptionHandler(PetNotFoundException::class)
    fun handlePetNotFoundException(exception: PetNotFoundException): ResponseEntity<String> {
        return ResponseEntity(exception.message, HttpStatus.NOT_FOUND)
    }
    @ExceptionHandler(DuplicatePetException::class)
    fun handleDuplicatePetException(exception: DuplicatePetException): ResponseEntity<String> {
        return ResponseEntity(exception.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
