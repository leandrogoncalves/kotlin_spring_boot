package dev.leandro.kotlin_spring_boot.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "person")
data class Person (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "first_name", nullable = false, length = 80)
    var firstName: String = "",

    @Column(name = "last_name", nullable = false, length = 80)
    var lastName: String = "",

    @Column(name = "email", nullable = false, length = 80, unique = true)
    var email: String = "",

    @Column(name = "address", nullable = false, length = 200)
    var address: String = "",

    @Column(name = "gender", nullable = false, length = 10)
    var gender: String = ""
)