package com.example.pruebatecnicaalten.data.network

import com.example.pruebatecnicaalten.domain.model.UsersResult
import retrofit2.http.GET
import retrofit2.http.Query


interface UsersRetrofitService {
    //Get Users with pagination
    @GET(ApiConstants.API_PATH)
    suspend fun getUsers(@Query("page") page: Int, @Query("results") results: Int): UsersResult?

}