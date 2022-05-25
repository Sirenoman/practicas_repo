package com.jonathancabrera.dummydictionary.network

import android.net.Credentials
import com.jonathancabrera.dummydictionary.network.dto.LoginRequest
import com.jonathancabrera.dummydictionary.network.dto.LoginResponse
import com.jonathancabrera.dummydictionary.network.dto.WordResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WordService {

    @GET("/word")
    suspend fun  getAllWord(): WordResponse

    @POST("/login")
    suspend fun  login(@Body credentials: LoginRequest): LoginResponse

}