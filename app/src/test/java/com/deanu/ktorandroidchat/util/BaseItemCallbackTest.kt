package com.deanu.ktorandroidchat.util

import com.deanu.ktorandroidchat.domain.model.Message
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BaseItemCallbackTest {

    @Test
    fun `equalsTo should return true when objects are the same`() {
        // Given
        val messageOne = Message("text", "Jul 30, 2022", "username")

        // When
        val result = messageOne.equalsTo(messageOne)

        // Then
        assertTrue(result)
    }

    @Test
    fun `equalsTo should return false when the objects contents are not the same`() {
        // Given
        val messageOne = Message("text", "Jul 30, 2022", "username")
        val messageTwo = Message("text two", "Jul 30, 2022", "username 2")

        // When
        val result = messageOne.equalsTo(messageTwo)

        // Then
        assertFalse(result)
    }

    @Test
    fun `equalsTo should return false when objects are not the same`() {
        // Given
        val messageOne = "text two"
        val messageTwo = Message("text two", "Jul 30, 2022", "username 2")

        // When
        val result = messageOne.equalsTo(messageTwo)

        // Then
        assertFalse(result)
    }
}