package dev.leandro.kotlin_spring_boot.util

import dev.leandro.kotlin_spring_boot.exceptions.DateConversionException
import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    fun stringToDate(dateString: String?): Date? {
        try {
            return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                .parse(dateString)
        } catch (e: Exception) {
            throw DateConversionException("Não foi possível converter a data de nascimento, use o formato dd/mm/YYYY")
        }
    }

    fun dateToString(date: Date?): String {
        return try {
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                .format(date)
        } catch (e: Exception) {
            ""
        }
    }
}