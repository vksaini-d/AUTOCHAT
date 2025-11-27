package com.example.autochat.data.remote

import retrofit2.http.Body
import retrofit2.http.POST

interface AutoChatApi {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
}

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val access_token: String, val user: UserDto)
data class UserDto(val id: String, val email: String, val name: String?)
