package com.android_academy.backend.api.models

import com.android_academy.backend.AppConfig
import com.android_academy.backend.db.models.LessonEntity
import com.fasterxml.jackson.annotation.JsonProperty

class UpdateLessonRequestDTO(
    @JsonProperty("id")
    val id: Long? = null,
    @JsonProperty("title")
    val title: String,
    @JsonProperty("youtube_url")
    val youtubeUrl: String = "",
    @JsonProperty("github_repo_url")
    val githubRepoUrl: String = "",
    @JsonProperty("telegram_channel")
    val telegramChannel: String = "",
    @JsonProperty("additional_materials")
    val additionalMaterials: List<AdditionalMaterialDTO>,
    @JsonProperty("img_url")
    val imgUrl: String? = null,
    @JsonProperty("tags")
    val tags: List<String>,
    @JsonProperty("course_id")
    val courseId: Long,
    @JsonProperty("start_timestamp_sec")
    val startTimeStampSec: Long,
    @JsonProperty("end_timestamp_sec")
    val endTimeStampSec: Long,
    @JsonProperty("favourite")
    val isFavourite: Boolean
)

fun UpdateLessonRequestDTO.toLesson(): LessonEntity =
    LessonEntity(
        id = id,
        title = title,
        youtubeUrl = youtubeUrl,
        githubRepoUrl = githubRepoUrl,
        telegramChannel = telegramChannel,
        additionalMaterials = AppConfig.objectMapper.writeValueAsString(additionalMaterials),
        imgUrl = imgUrl,
        tags = tags.joinToString(),
        courseId = courseId,
        startTimestampSec = startTimeStampSec,
        endTimestampSec = endTimeStampSec,
        isFavourite = isFavourite
    )
