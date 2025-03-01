package com.example.quotegenerator.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "http://api.quotable.io/"

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    })
    .build()

private val retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()


interface ApiService {
    @GET("/random")
    suspend fun getRandomQuote(): QuoteResponse

    @GET("/quotes/{id}")
    suspend fun getQuoteById(@Path(value = "id") id: String): QuoteResponse
}

object QuoteApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}