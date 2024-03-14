package com.example.pruebatecnicaalten.data.repository

import com.example.pruebatecnicaalten.data.datasource.IUsersApiDataSource
import com.example.pruebatecnicaalten.domain.model.UsersResult

class UsersRepository(private val dataSource: IUsersApiDataSource) : IUsersRepository {
    override suspend fun getUsers(page: Int, results: Int): UsersResult? {

        return dataSource.getUsers(page, results)

    }

}