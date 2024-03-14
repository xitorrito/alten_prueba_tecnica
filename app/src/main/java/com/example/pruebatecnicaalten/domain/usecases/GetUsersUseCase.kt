package com.example.pruebatecnicaalten.domain.usecases

import com.example.pruebatecnicaalten.data.repository.IUsersRepository
import com.example.pruebatecnicaalten.domain.model.UsersResult
import javax.inject.Inject


class GetUsersUseCase @Inject constructor(
    private val repository: IUsersRepository
) {

    suspend operator fun invoke(page: Int, results: Int): UsersResult? {
        return repository.getUsers(page, results)
    }

}