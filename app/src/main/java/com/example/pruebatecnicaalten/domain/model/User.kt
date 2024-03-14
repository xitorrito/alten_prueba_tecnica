package com.example.pruebatecnicaalten.domain.model


data class User(

    var gender: String? = null,
    var name: Name? = Name(),
    var location: Location? = Location(),
    var email: String? = null,
    var login: Login? = Login(),
    var dob: Dob? = Dob(),
    var registered: Registered? = Registered(),
    var phone: String? = null,
    var cell: String? = null,
    var id: Id? = Id(),
    var picture: Picture? = Picture(),
    var nat: String? = null

)