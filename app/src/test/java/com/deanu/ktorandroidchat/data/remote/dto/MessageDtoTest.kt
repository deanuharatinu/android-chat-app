package com.deanu.ktorandroidchat.data.remote.dto

import com.deanu.ktorandroidchat.domain.model.Message
import org.junit.Assert
import org.junit.Test

class MessageDtoTest {
    private lateinit var expectedMessage: Message
    private val message: String = "this is message"
    private val username = "username"
    private val timestamp = 1659188570877 // Jul 30, 2022
    private val formattedTimestamp = "Jul 30, 2022"

    @Test
    fun `results are equals`() {
        // Given
        expectedMessage = Message(
            message,
            formattedTimestamp,
            username
        )

        // When
        val resultMessage = MessageDto(
            message,
            timestamp,
            username,
            "1"
        ).toMessage()

        // Then
        Assert.assertEquals(expectedMessage, resultMessage)
    }

}