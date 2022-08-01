package com.deanu.ktorandroidchat.presentation.chat

import com.deanu.ktorandroidchat.domain.model.Message

data class ChatState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)
