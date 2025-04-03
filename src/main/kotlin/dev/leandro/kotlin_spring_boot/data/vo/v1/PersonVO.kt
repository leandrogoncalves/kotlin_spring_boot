package dev.leandro.kotlin_spring_boot.data.vo.v1

data class PersonVO (
    var id: Long = 0,
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var address: String = "",
    var gender: String = ""
)