package com.android_academy.backend.api.models

import com.android_academy.backend.domain.models.UserRole

class RegisterRequestDTO(
    val username: String,
    val pwd: String,
    val name: String,
    val userRole: UserRole
)
