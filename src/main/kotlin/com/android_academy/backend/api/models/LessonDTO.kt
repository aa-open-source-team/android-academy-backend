package com.android_academy.backend.api.models

import com.android_academy.backend.AppConfig
import com.android_academy.backend.db.models.LessonEntity
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.readValue

class LessonDTO(
    @JsonProperty("id")
    val id: Long,
    @JsonProperty("title")
    val title: String,
    @JsonProperty("short_description")
    val shortDescription: String? = null,
    @JsonProperty("full_description")
    val fullDescription: String? = null,
    @JsonProperty("img_url")
    val imgUrl: String? = null,
    @JsonProperty("youtube_url")
    val youtubeUrl: String = "",
    @JsonProperty("github_repo_url")
    val githubRepoUrl: String = "",
    @JsonProperty("telegram_channel")
    val telegramChannel: String = "",
    @JsonProperty("additional_materials")
    val additionalMaterials: List<AdditionalMaterialDTO>,
    @JsonProperty("tags")
    val tags: List<String>,
    @JsonProperty("course_id")
    val courseId: Long,
    @JsonProperty("start_timestamp_sec")
    val startTimestampSec: Long,
    @JsonProperty("end_timestamp_sec")
    val endTimestampSec: Long,
    @JsonProperty("favourite")
    val isFavourite: Boolean
)

fun fromLesson(lesson: LessonEntity): LessonDTO =
    with(lesson) {
        LessonDTO(
            id = id!!,
            title = title,
            shortDescription = shortDescription,
            fullDescription = fullDescription,
            youtubeUrl = youtubeUrl,
            githubRepoUrl = githubRepoUrl,
            telegramChannel = telegramChannel,
            additionalMaterials = AppConfig.objectMapper.readValue(additionalMaterials),
            imgUrl = imgUrl,
            courseId = courseId,
            tags = tags.split(", "),
            startTimestampSec = startTimestampSec,
            endTimestampSec = endTimestampSec,
            isFavourite = isFavourite
        )
    }
