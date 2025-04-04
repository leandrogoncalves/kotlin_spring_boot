package dev.leandro.kotlin_spring_boot.data.vo.v2

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.springframework.hateoas.RepresentationModel

@JsonPropertyOrder("id", "lastName", "firstName", "address", "email", "gender")
data class PersonVO (
    var id: Long = 0,

    @field:JsonProperty("first_name")
    var firstName: String = "",

    @field:JsonProperty("last_name")
    var lastName: String = "",

    var email: String = "",

    var address: String = "",

    @field:JsonIgnore
    var gender: String = "",

    var birthDay: String? = "",
) : RepresentationModel<PersonVO>()