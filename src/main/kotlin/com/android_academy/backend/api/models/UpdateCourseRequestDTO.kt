package com.android_academy.backend.api.models

import com.android_academy.backend.AppConfig
import com.android_academy.backend.db.models.CourseEntity
import com.android_academy.backend.domain.models.City
import com.android_academy.backend.domain.models.CourseLanguage
import com.android_academy.backend.domain.models.CourseMode

class UpdateCourseRequestDTO(
    val id: Long? = null,
    val title: String,
    val shortDescription: String? = null,
    val fullDescription: String? = null,
    val imgUrl: String? = null,
    val tags: List<String>,
    val isSubscribed: Boolean = false,
    val startTimeStampSec: Long,
    val endTimeStampSec: Long,
    val language: CourseLanguage,
    val hostCities: List<City>,
    val mode: CourseMode
)

fun UpdateCourseRequestDTO.toCourse(): CourseEntity =
    CourseEntity(
        id = id,
        title = title,
        shortDescription = shortDescription,
        fullDescription = fullDescription,
        imgUrl = imgUrl,
        tags = tags.joinToString(),
        isSubscribed = isSubscribed,
        startTimestampSec = startTimeStampSec,
        endTimestampSec = endTimeStampSec,
        language = language,
        hostCities = AppConfig.objectMapper.writeValueAsString(hostCities),
        mode = mode
    )
