package com.example.gptpractice.Data

import com.example.gptpractice.Data.OpenAiApi.Companion.API_KEY
import com.example.gptpractice.Tools.CompletionRequest
import com.example.gptpractice.Tools.CompletionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface OpenAiApi {
    @Headers("Authoriztion: Bearer $API_KEY")
    @POST("v1/completions")
    suspend fun getCompletiongs(
        @Body completionRequest: CompletionRequest
    ): Response<CompletionResponse>


    companion object{
        const val API_KEY = "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"
    }
}