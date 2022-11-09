package com.android_academy.backend.api.models

import com.android_academy.backend.domain.models.UserRole
import com.fasterxml.jackson.annotation.JsonProperty

class UserProfileDTO(
    @JsonProperty("username")
    val username: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("user_role")
    val userRole: UserRole
)
