package com.montoya.sem10_servicios.data.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.montoya.sem10_servicios.data.remote.ApiService
import com.montoya.sem10_servicios.data.model.User

class UserRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)


    suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }
}