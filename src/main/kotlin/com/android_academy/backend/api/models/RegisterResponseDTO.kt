package com.android_academy.backend.api.models

import com.fasterxml.jackson.annotation.JsonProperty

class RegisterResponseDTO(
    @JsonProperty("token")
    val token: String,
    @JsonProperty("user_profile")
    val userProfile: UserProfileDTO
)
