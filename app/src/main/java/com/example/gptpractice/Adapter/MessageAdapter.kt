package com.example.gptpractice.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gptpractice.R
import com.example.gptpractice.Tools.Message

class MessageAdapter(
    private val messageList: List<Message>
    ): RecyclerView.Adapter<MessageAdapter.MessageViewHolder>(){

    companion object{

                const val SENT_BY_ME = 0
                const val SENT_BY_BOT = 1
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageAdapter.MessageViewHolder {

        return when(viewType){
            SENT_BY_ME ->{
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.user_chat, parent, false)
                MessageViewHolder(view)
            }
            else ->{
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.bot_chat, parent, false)
                MessageViewHolder(view)
            }
        }
    }




    override fun onBindViewHolder(holder: MessageAdapter.MessageViewHolder, position: Int) {
        val message = messageList[position]
        holder.bind(message)
    }

    override fun getItemCount(): Int  = messageList.size

    override fun getItemViewType(position: Int): Int {
        val message = messageList[position]

        return when(message.sentBy){
                Message.SENT_BY_ME -> SENT_BY_ME
                else -> SENT_BY_BOT
        }
    }



    inner class MessageViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val leftChatTextView: TextView? = itemView.findViewById(R.id.left_chat_text_view)
        private val leftChatTimestamp: TextView? = itemView.findViewById(R.id.left_chat_timestamp)
        private val righChatTextView: TextView? = itemView.findViewById(R.id.left_chat_text_view)
        private val rightChatTimestamp: TextView? = itemView.findViewById(R.id.right_chat_timestamp)

        fun bind(message: Message){
            when(message.sentBy){
                Message.SENT_BY_ME ->{
                    righChatTextView?.text = message.message
                    rightChatTimestamp?.text = message.timeStamp
                }
            }
        }


    }

}