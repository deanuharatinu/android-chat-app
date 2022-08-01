package com.deanu.ktorandroidchat.data.remote

import com.deanu.ktorandroidchat.domain.model.Message
import com.deanu.ktorandroidchat.util.Resource
import kotlinx.coroutines.flow.Flow

interface ChatSocketService {

    suspend fun initSession(username: String): Resource<Unit>

    suspend fun sendMessage(message: String)

    fun observeMessage(): Flow<Message>

    suspend fun closeSession()

    sealed class EndPoints(val url: String) {
        object ChatSocket : EndPoints("$BASE_URL/chat-socket")
    }

    companion object {
        const val BASE_URL = "ws://192.168.1.21:8082"
    }

}