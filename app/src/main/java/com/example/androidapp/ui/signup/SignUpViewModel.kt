package com.example.androidapp.ui.signup

import androidx.lifecycle.ViewModel
import com.example.androidapp.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SignUpUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val error: String? = null,
    val isSignedUp: Boolean = false
)

class SignUpViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState = _uiState.asStateFlow()

    fun onNameChange(value: String) {
        _uiState.update { it.copy(name = value, error = null) }
    }

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value, error = null) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value, error = null) }
    }

    fun signUp() {
        val state = _uiState.value
        AuthRepository.signUp(state.name, state.email, state.password)
            .onSuccess {
                _uiState.update { it.copy(isSignedUp = true, error = null) }
            }
            .onFailure { e ->
                _uiState.update { it.copy(error = e.message) }
            }
    }
}
