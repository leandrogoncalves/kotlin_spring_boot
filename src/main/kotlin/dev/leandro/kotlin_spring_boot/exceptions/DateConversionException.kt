package dev.leandro.kotlin_spring_boot.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class DateConversionException(exception: String?): RuntimeException(exception)