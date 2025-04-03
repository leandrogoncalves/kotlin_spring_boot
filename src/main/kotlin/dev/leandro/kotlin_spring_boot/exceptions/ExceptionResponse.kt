package dev.leandro.kotlin_spring_boot.exceptions

import java.util.Date

class ExceptionResponse(
    val timestamp: Date,
    val message: String?,
    val details: String
)