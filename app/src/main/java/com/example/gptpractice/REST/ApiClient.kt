package com.example.gptpractice.REST

import com.example.gptpractice.Data.OpenAiApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object ApiClient {

    private const val BASE_URL = "https://api.openai.com/"

    private val httpClient =
                OkHttpClient.Builder().build()

    private val retrofit =
                Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()

    val apiService: OpenAiApi = retrofit.create(OpenAiApi::class.java)
}