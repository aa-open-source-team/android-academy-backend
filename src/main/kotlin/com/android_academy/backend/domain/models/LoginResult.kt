package com.android_academy.backend.domain.models

import com.android_academy.backend.db.models.UserEntity

data class LoginResult(
    val success: Boolean,
    val refreshToken: String,
    val user: UserEntity?
)
