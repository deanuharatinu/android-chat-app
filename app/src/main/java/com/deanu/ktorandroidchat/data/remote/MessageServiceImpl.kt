package com.deanu.ktorandroidchat.data.remote

import com.deanu.ktorandroidchat.data.remote.dto.MessageDto
import com.deanu.ktorandroidchat.domain.model.Message
import io.ktor.client.*
import io.ktor.client.request.*

class MessageServiceImpl(
    private val client: HttpClient
) : MessageService {

    override suspend fun getAllMessages(): List<Message> {
        return try {
            client.get<List<MessageDto>>(MessageService.EndPoints.GetAllMessages.url)
                .map { it.toMessage()  }
        } catch (e: Exception) {
            emptyList()
        }
    }

}