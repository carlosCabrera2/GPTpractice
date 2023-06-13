package com.example.gptpractice.Tools

data class CompletionRequest(
    val model: String,
    val prompt: String,
    val max_tokens:Int,
    val temperature: Float = 0F

//    val top_p: Double,
//    val n : Int,
//    val stream: Boolean,
//    val logprobs: Int?,
//    val stop: String
)
