package com.example.androidapp.ui.login

import androidx.lifecycle.ViewModel
import com.example.androidapp.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val error: String? = null,
    val isLoggedIn: Boolean = false
)

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value, error = null) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value, error = null) }
    }

    fun login() {
        val state = _uiState.value
        AuthRepository.login(state.email, state.password)
            .onSuccess {
                _uiState.update { it.copy(isLoggedIn = true, error = null) }
            }
            .onFailure { e ->
                _uiState.update { it.copy(error = e.message) }
            }
    }
}
