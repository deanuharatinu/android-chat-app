package com.deanu.ktorandroidchat.presentation.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deanu.ktorandroidchat.databinding.ChatSentMessageItemBinding
import com.deanu.ktorandroidchat.domain.model.Message
import com.deanu.ktorandroidchat.util.BaseItemCallback

class ChatAdapter : ListAdapter<Message, ChatAdapter.ViewHolder>(BaseItemCallback<Message>()) {

    class ViewHolder(private val binding: ChatSentMessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            binding.tvUsername.text = message.username
            binding.tvMessage.text = message.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ChatSentMessageItemBinding = ChatSentMessageItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = getItem(position)
        holder.bind(message)
    }

}