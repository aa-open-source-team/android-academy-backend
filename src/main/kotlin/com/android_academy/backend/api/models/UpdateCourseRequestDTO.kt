package com.android_academy.backend.api.models

import com.android_academy.backend.AppConfig
import com.android_academy.backend.db.models.CourseEntity
import com.android_academy.backend.domain.models.City
import com.android_academy.backend.domain.models.CourseLanguage
import com.android_academy.backend.domain.models.CourseMode
import com.fasterxml.jackson.annotation.JsonProperty

class UpdateCourseRequestDTO(
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
    @JsonProperty("subscribed")
    val isSubscribed: Boolean = false,
    @JsonProperty("start_timestamp_sec")
    val startTimeStampSec: Long,
    @JsonProperty("end_timestamp_sec")
    val endTimeStampSec: Long,
    @JsonProperty("language")
    val language: CourseLanguage,
    @JsonProperty("host_cities")
    val hostCities: List<City>,
    @JsonProperty("mode")
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
