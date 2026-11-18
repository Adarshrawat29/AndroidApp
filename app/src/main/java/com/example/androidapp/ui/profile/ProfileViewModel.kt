package com.example.androidapp.ui.profile

import androidx.lifecycle.ViewModel
import com.example.androidapp.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {

    private val _user = MutableStateFlow(AuthRepository.getCurrentUser())
    val user = _user.asStateFlow()

    fun logout() {
        AuthRepository.logout()
        _user.value = null
    }
}
