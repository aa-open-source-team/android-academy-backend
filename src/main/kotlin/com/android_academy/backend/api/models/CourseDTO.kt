package com.android_academy.backend.api.models

import com.android_academy.backend.AppConfig
import com.android_academy.backend.db.models.CourseEntity
import com.android_academy.backend.domain.models.City
import com.android_academy.backend.domain.models.CourseLanguage
import com.android_academy.backend.domain.models.CourseMode
import com.fasterxml.jackson.module.kotlin.readValue

class CourseDTO(
    val id: Long,
    val title: String,
    val shortDescription: String? = null,
    val fullDescription: String? = null,
    val imgUrl: String? = null,
    val tags: List<String>,
    val language: CourseLanguage,
    val startTimestampSec: Long,
    val endTimestampSec: Long,
    val hostCities: List<City>,
    val mode: CourseMode,
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
