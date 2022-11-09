package com.android_academy.backend.api.models

import com.fasterxml.jackson.annotation.JsonProperty

class AdditionalMaterialDTO(
    @JsonProperty("topic_name")
    val topicName: String,
    @JsonProperty("url")
    val url: String
)
