package com.example.gptpractice.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gptpractice.REST.ApiClient
import com.example.gptpractice.Tools.CompletionRequest
import com.example.gptpractice.Tools.CompletionResponse
import com.example.gptpractice.Tools.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.SocketTimeoutException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class GptViewModel: ViewModel() {

    private val _messageList = MutableLiveData<MutableList<Message>>()
    val messageList : LiveData<MutableList<Message>> get() = _messageList

    init {
        _messageList.value = mutableListOf()
    }

    fun addToChat(message: String, sentBy: String, timeStamp: String){
        val currentList = _messageList.value?: mutableListOf()
        currentList.add(Message(message, sentBy, timeStamp))
    }

    fun addResponse(response: String){
        _messageList.value?.removeAt(_messageList.value?.size?.minus(1)?:0)
        addToChat(response, Message.SENT_BY_BOT, getCurrentTimeStamp())
    }

    fun callApi(question: String){
        addToChat("Typing...", Message.SENT_BY_BOT, getCurrentTimeStamp())

        val completionRequest =CompletionRequest(
                model = "text-divinci-003",
                prompt = question,
                max_tokens = 4000
        )

        viewModelScope.launch {
            try{
                val response= ApiClient.apiService.getCompletiongs(completionRequest)
                handleApiResponse(response)
        }catch (e: SocketTimeoutException){
            addResponse("Timeout : $e")
            }
        }
    }


    private suspend fun handleApiResponse(response: Response<CompletionResponse>){
        withContext(Dispatchers.Main){
            if(response.isSuccessful){
                response?.body()?.let {
                    completionResponse ->
                        val result = completionResponse.choices.firstOrNull()?.text

                        if( result != null){
                            addResponse(result.trim())
                        }else {

                            addResponse("No Choices Found")
                        }
                }
            }else{
                addResponse("Failed to get response ${response.errorBody()}")
            }
        }
    }

    fun getCurrentTimeStamp(): String =
        SimpleDateFormat("hh mm a", Locale.getDefault()).format(Date())

}