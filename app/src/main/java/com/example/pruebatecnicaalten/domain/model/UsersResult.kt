package com.example.pruebatecnicaalten.domain.model


data class UsersResult(

    var results: List<User> = listOf(),
    var info: Info? = Info()

)