package com.example.gptpractice.Tools

data class Message(
    val message:String,
    val sentBy: String,
    val timeStamp:String
                ){
                companion object{

                    const val SENT_BY_ME = "i_sent"
                    const val SENT_BY_BOT = "host_sent"

                }
}
