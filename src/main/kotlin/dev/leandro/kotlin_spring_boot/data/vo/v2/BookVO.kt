package dev.leandro.kotlin_spring_boot.data.vo.v2

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import jakarta.persistence.*
import org.springframework.hateoas.RepresentationModel
import java.util.Date

@JsonPropertyOrder("id", "author", "launchDate", "price", "title")
data class BookVO (

    @field:JsonProperty("id")
    var id: Long = 0,
    var author: String = "",
    var launchDate: String? = null,
    var price: Double = 0.0,
    var title: String = ""
) : RepresentationModel<PersonVO>()