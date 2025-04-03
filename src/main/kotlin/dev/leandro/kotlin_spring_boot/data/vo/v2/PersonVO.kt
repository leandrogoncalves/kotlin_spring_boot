package dev.leandro.kotlin_spring_boot.data.vo.v2

import java.util.Date

data class PersonVO (
    var id: Long = 0,
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var address: String = "",
    var gender: String = "",
    var birthDay: String? = "",
)