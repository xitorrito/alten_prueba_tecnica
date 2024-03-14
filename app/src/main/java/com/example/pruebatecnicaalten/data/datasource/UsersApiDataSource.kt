package com.example.pruebatecnicaalten.data.datasource

import com.example.pruebatecnicaalten.data.network.RetrofitClient
import com.example.pruebatecnicaalten.data.network.UsersRetrofitService
import com.example.pruebatecnicaalten.domain.model.UsersResult


class UsersApiDataSource : IUsersApiDataSource {

    private val client = RetrofitClient.retrofitClient.create(UsersRetrofitService::class.java)

    override suspend fun getUsers(page: Int, results: Int): UsersResult? {
        return client.getUsers(page = page, results = results)
    }
}