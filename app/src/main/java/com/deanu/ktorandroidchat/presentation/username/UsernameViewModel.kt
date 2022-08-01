package com.deanu.ktorandroidchat.presentation.username

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsernameViewModel @Inject constructor() : ViewModel() {

    private val _usernameText = MutableStateFlow("")
    val usernameText: StateFlow<String> = _usernameText

    private val _onJoinChat = MutableSharedFlow<String>()
    val onJoinChat = _onJoinChat.asSharedFlow()

    private val _usernameError = MutableLiveData(false)
    val usernameError: LiveData<Boolean> = _usernameError

    fun onUsernameChange(username: String) {
        _usernameText.value = username
    }

    fun onJoinClick() {
        viewModelScope.launch {
            if (usernameText.value.isNotBlank() &&
                usernameText.value.isNotEmpty()
            ) {
                _usernameError.value = false
                _onJoinChat.emit(usernameText.value)
            } else {
                _usernameError.value = true
            }
        }
    }

}