package com.android_academy.backend.api.models

import com.fasterxml.jackson.annotation.JsonProperty

class LoginRequestDTO(
    @JsonProperty("username")
    val username: String,
    @JsonProperty("pwd")
    val pwd: String
)
