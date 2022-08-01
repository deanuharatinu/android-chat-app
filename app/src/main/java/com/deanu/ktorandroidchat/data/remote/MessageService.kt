package com.deanu.ktorandroidchat.data.remote

import com.deanu.ktorandroidchat.domain.model.Message

interface MessageService {

    suspend fun getAllMessages(): List<Message>

    sealed class EndPoints(val url: String) {
        object GetAllMessages: EndPoints("$BASE_URL/messages")
    }

    companion object {
        const val BASE_URL = "http://192.168.1.21:8082"
    }
}