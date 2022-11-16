package com.android_academy.backend.api.models

import com.android_academy.backend.AppConfig
import com.android_academy.backend.db.models.CourseEntity
import com.android_academy.backend.domain.models.City
import com.android_academy.backend.domain.models.CourseLanguage
import com.android_academy.backend.domain.models.CourseMode
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.readValue

class CourseDTO(
    @JsonProperty("id")
    val id: Long? = null,
    @JsonProperty("title")
    val title: String,
    @JsonProperty("short_description")
    val shortDescription: String? = null,
    @JsonProperty("full_description")
    val fullDescription: String? = null,
    @JsonProperty("img_url")
    val imgUrl: String? = null,
    @JsonProperty("tags")
    val tags: List<String>,
    @JsonProperty("language")
    val language: CourseLanguage,
    @JsonProperty("start_timestamp_sec")
    val startTimestampSec: Long,
    @JsonProperty("end_timestamp_sec")
    val endTimestampSec: Long,
    @JsonProperty("host_cities")
    val hostCities: List<City> = emptyList(),
    @JsonProperty("mode")
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

fun CourseDTO.toCourse(): CourseEntity =
    CourseEntity(
        id = id,
        title = title,
        shortDescription = shortDescription,
        fullDescription = fullDescription,
        imgUrl = imgUrl,
        tags = tags.joinToString(),
        isSubscribed = isSubscribed,
        startTimestampSec = startTimestampSec,
        endTimestampSec = endTimestampSec,
        language = language,
        hostCities = AppConfig.objectMapper.writeValueAsString(hostCities),
        mode = mode
    )
