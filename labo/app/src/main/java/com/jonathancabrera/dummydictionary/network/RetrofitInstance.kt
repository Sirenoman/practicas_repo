package com.jonathancabrera.dummydictionary.network

import com.jonathancabrera.dummydictionary.network.WordService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URI = "http://10.0.2.2:3000/"
object RetrofitInstance {
    private val interceptorLogging = HttpLoggingInterceptor().apply{
        level = HttpLoggingInterceptor.Level.BODY
    }
    private var token = ""

    fun setToken(value:String){
        token = value
    }
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URI)
        .client(
            OkHttpClient()
                .newBuilder()
                .addInterceptor{chain ->
                    chain.proceed(
                        chain
                            .request()
                            .newBuilder()
                            .addHeader("Authorization", "BEARER $token")
                            .build()
                    )
                }.addInterceptor(interceptorLogging)
                .build()

        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getWordService(): WordService {
        return retrofit.create(WordService::class.java)
    }
}