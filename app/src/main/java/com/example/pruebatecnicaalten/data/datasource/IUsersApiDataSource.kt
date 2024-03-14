package com.example.pruebatecnicaalten.data.datasource

import com.example.pruebatecnicaalten.domain.model.UsersResult

interface IUsersApiDataSource {
    suspend fun getUsers(page: Int, results: Int) : UsersResult?
}