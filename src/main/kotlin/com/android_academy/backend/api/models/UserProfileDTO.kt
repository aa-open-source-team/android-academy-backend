package com.android_academy.backend.api.models

import com.android_academy.backend.domain.models.UserRole

class UserProfileDTO(
    val username: String,
    val name: String,
    val userRole: UserRole
)
