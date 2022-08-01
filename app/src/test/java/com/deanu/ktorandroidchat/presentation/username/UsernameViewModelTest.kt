package com.deanu.ktorandroidchat.presentation.username

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class UsernameViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val dispatcher = UnconfinedTestDispatcher()
    private val viewModel: UsernameViewModel = UsernameViewModel()
    private val result = mutableListOf<String>()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `should equals, on call onUsernameChange`() = runTest {
        // Given
        val expectedResult = mutableListOf("username1")
        val job = launch { viewModel.usernameText.toList(result) }

        // When
        viewModel.onUsernameChange("username1")
        runCurrent()

        // Then
        assertEquals(expectedResult, result)
        job.cancel()
    }

    @Test
    fun `should equals, on double call onUsernameChange`() = runTest {
        // Given
        val expectedResult = mutableListOf("username 1", "username 2")
        val job = launch { viewModel.usernameText.toList(result) }

        // When
        viewModel.onUsernameChange("username 1")
        runCurrent()
        viewModel.onUsernameChange("username 2")
        runCurrent()

        // Then
        assertEquals(expectedResult, result)
        job.cancel()
    }

    @Test
    fun `should equals, onJoinClick when username is not blank nor empty`() = runTest {
        // Given
        val job = launch { viewModel.onJoinChat.toList(result) }

        // When
        viewModel.onUsernameChange("username 1")
        runCurrent()
        viewModel.onJoinClick()
        runCurrent()

        // Then
        assertEquals("username 1", result[0])
        job.cancel()
    }

    @Test
    fun `should make onJoinChat empty, when onJoinClick and username is blank`() = runTest {
        // Given
        val job = launch { viewModel.onJoinChat.toList(result) }

        // When
        viewModel.onUsernameChange("")
        runCurrent()
        viewModel.onJoinClick()
        runCurrent()

        // Then
        assertTrue(result.isEmpty())
        job.cancel()
    }

    @Test
    fun `should make onJoinChat empty, when onJoinClick and username is empty`() = runTest {
        // Given
        val job = launch { viewModel.onJoinChat.toList(result) }

        // When
        viewModel.onUsernameChange(" ")
        runCurrent()
        viewModel.onJoinClick()
        runCurrent()

        // Then
        assertTrue(result.isEmpty())
        job.cancel()
    }

    @Test
    fun `should make onJoinChat empty, when onJoinClick and username is not filled`() = runTest {
        // Given
        val job = launch { viewModel.onJoinChat.toList(result) }

        // When
        viewModel.onJoinClick()
        runCurrent()

        // Then
        assertTrue(result.isEmpty())
        job.cancel()
    }

    @Test
    fun `should make usernameError is true, when onJoinClick and username is blank`() {
        // Given
        val mockObserver = mock<Observer<Boolean>>()
        viewModel.usernameError.observeForever(mockObserver)

        // When
        viewModel.onUsernameChange(" ")
        viewModel.onJoinClick()

        // Then
        verify(mockObserver).onChanged(true)
    }

    @Test
    fun `should make usernameError is false, when onJoinClick and username is not empty, `() {
        // Given
        val mockObserver = mock<Observer<Boolean>>()
        viewModel.usernameError.observeForever(mockObserver)

        // When
        viewModel.onUsernameChange("username")
        viewModel.onJoinClick()

        // Then
        verify(mockObserver, times(2)).onChanged(false)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}