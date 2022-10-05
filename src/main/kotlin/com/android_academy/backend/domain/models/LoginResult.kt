package com.android_academy.backend.domain.models

import com.android_academy.backend.db.models.UserEntity

data class LoginResult(
    val success: Boolean,
    val token: String,
    val user: UserEntity?
)
