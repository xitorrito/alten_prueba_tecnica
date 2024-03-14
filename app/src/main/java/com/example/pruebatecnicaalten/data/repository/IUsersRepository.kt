package com.example.pruebatecnicaalten.data.repository

import com.example.pruebatecnicaalten.domain.model.UsersResult

interface IUsersRepository {
    suspend fun getUsers(page: Int, results: Int): UsersResult?
}