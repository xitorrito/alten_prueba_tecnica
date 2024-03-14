package com.example.pruebatecnicaalten.domain.model

import com.example.pruebatecnicaalten.domain.model.Timezone


data class Location(

    var street: Street? = Street(),
    var city: String? = null,
    var state: String? = null,
    var country: String? = null,
    var postcode: String? = null,
    var coordinates: Coordinates? = Coordinates(),
    var timezone: Timezone? = Timezone()

)