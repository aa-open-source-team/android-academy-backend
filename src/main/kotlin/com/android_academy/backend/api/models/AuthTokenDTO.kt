package com.android_academy.backend.api.models

import com.fasterxml.jackson.annotation.JsonProperty

class AuthTokenDTO(
    @JsonProperty("auth_oken")
    val authToken: String
)
