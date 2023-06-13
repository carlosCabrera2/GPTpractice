package com.example.gptpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.gptpractice.Adapter.MessageAdapter
import com.example.gptpractice.Tools.Message
import com.example.gptpractice.ViewModel.GptViewModel
import com.example.gptpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var _binding : ActivityMainBinding
    private lateinit var gptViewModel: GptViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding= ActivityMainBinding.inflate(layoutInflater)

        val binding = _binding.root

        setContentView(binding)

        gptViewModel = ViewModelProvider(this)[GptViewModel::class.java]

        gptViewModel.messageList.observe(this){messages ->

                    val adapter= MessageAdapter(messages)
                    _binding.recyclerView.adapter = adapter
        }

        _binding.sendBtn.setOnClickListener {

                val question = _binding.messageEditText.text.toString()
                gptViewModel.addToChat(question, Message.SENT_BY_ME, gptViewModel.getCurrentTimeStamp())
                _binding.messageEditText.setText("")
                gptViewModel.callApi(question)
        }


    }
}