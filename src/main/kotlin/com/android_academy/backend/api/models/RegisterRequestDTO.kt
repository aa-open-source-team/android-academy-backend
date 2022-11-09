package com.android_academy.backend.api.models

import com.android_academy.backend.domain.models.UserRole
import com.fasterxml.jackson.annotation.JsonProperty

class RegisterRequestDTO(
    @JsonProperty("username")
    val username: String,
    @JsonProperty("pwd")
    val pwd: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("user_role")
    val userRole: UserRole
)
