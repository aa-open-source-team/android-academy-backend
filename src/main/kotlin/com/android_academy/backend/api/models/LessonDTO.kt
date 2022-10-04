package com.android_academy.backend.api.models

import com.android_academy.backend.AppConfig
import com.android_academy.backend.db.models.Lesson
import com.fasterxml.jackson.module.kotlin.readValue

class LessonDTO(
    val id: Long,
    val title: String,
    val shortDescription: String? = null,
    val fullDescription: String? = null,
    val imgUrl: String? = null,
    val youtubeUrl: String = "",
    val githubRepoUrl: String = "",
    val telegramChannel: String = "",
    val additionalMaterials: List<AdditionalMaterialDTO>,
    val tags: List<String>,
    val courseId: Long,
    val startTimestampSec: Long,
    val endTimestampSec: Long,
    val isFavourite: Boolean
)

fun fromLesson(lesson: Lesson): LessonDTO =
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
