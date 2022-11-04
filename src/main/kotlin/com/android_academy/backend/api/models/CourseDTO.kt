package com.android_academy.backend.api.models

import com.android_academy.backend.AppConfig
import com.android_academy.backend.db.models.CourseEntity
import com.android_academy.backend.domain.models.City
import com.android_academy.backend.domain.models.CourseLanguage
import com.android_academy.backend.domain.models.CourseMode
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.readValue

class CourseDTO(
    val id: Long,
    val title: String,
    @JsonProperty("short_description")
    val shortDescription: String? = null,
    @JsonProperty("full_description")
    val fullDescription: String? = null,
    @JsonProperty("img_url")
    val imgUrl: String? = null,
    val tags: List<String>,
    val language: CourseLanguage,
    @JsonProperty("start_timestamp_sec")
    val startTimestampSec: Long,
    @JsonProperty("end_timestamp_sec")
    val endTimestampSec: Long,
    @JsonProperty("host_cities")
    val hostCities: List<City> = emptyList(),
    val mode: CourseMode,
    @JsonProperty("subscribed")
    val isSubscribed: Boolean = false
)

fun fromCourse(course: CourseEntity): CourseDTO =
    with(course) {
        CourseDTO(
            id = id!!,
            title = title,
            shortDescription = shortDescription,
            fullDescription = fullDescription,
            imgUrl = imgUrl,
            tags = tags?.split(", ") ?: emptyList(),
            isSubscribed = isSubscribed,
            language = language,
            startTimestampSec = startTimestampSec,
            endTimestampSec = endTimestampSec,
            hostCities = AppConfig.objectMapper.readValue(hostCities),
            mode = mode,
        )
    }
