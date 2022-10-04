package com.android_academy.backend.api.models

import com.android_academy.backend.AppConfig
import com.android_academy.backend.db.models.Lesson

class UpdateLessonRequestDTO(
    val id: Long? = null,
    val title: String,
    val youtubeUrl: String = "",
    val githubRepoUrl: String = "",
    val telegramChannel: String = "",
    val additionalMaterials: List<AdditionalMaterialDTO>,
    val imgUrl: String? = null,
    val tags: List<String>,
    val courseId: Long,
    val startTimeStampSec: Long,
    val endTimeStampSec: Long,
    val isFavourite: Boolean
)

fun UpdateLessonRequestDTO.toLesson(): Lesson =
    Lesson(
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
