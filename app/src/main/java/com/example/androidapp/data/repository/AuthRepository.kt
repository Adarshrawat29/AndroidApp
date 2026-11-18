package com.example.androidapp.data.repository

import com.example.androidapp.data.model.User

object AuthRepository {

    private val users = mutableMapOf<String, Pair<String, User>>()
    private var currentUser: User? = null

    fun login(email: String, password: String): Result<User> {
        if (email.isBlank() || password.isBlank()) {
            return Result.failure(Exception("Email and password required"))
        }
        val account = users[email]
        if (account == null || account.first != password) {
            return Result.failure(Exception("Invalid email or password"))
        }
        currentUser = account.second
        return Result.success(account.second)
    }

    fun signUp(name: String, email: String, password: String): Result<User> {
        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            return Result.failure(Exception("All fields are required"))
        }
        if (users.containsKey(email)) {
            return Result.failure(Exception("Account already exists"))
        }
        val user = User(name = name, email = email)
        users[email] = password to user
        currentUser = user
        return Result.success(user)
    }

    fun getCurrentUser(): User? = currentUser

    fun logout() {
        currentUser = null
    }
}
