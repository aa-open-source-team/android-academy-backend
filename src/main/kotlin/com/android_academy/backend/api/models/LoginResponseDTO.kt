package com.android_academy.backend.api.models

import com.fasterxml.jackson.annotation.JsonProperty

class LoginResponseDTO(
    @JsonProperty("user_profile")
    val userProfile: UserProfileDTO
)
