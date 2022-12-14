package com.android_academy.backend.db.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "lessons")
class LessonEntity(
    @DatabaseField(generatedId = true)
    val id: Long? = null,
    @DatabaseField
    val title: String,
    @DatabaseField
    val shortDescription: String? = null,
    @DatabaseField
    val fullDescription: String? = null,
    @DatabaseField
    val youtubeUrl: String = "",
    @DatabaseField
    val githubRepoUrl: String = "",
    @DatabaseField
    val telegramChannel: String = "",
    @DatabaseField
    val additionalMaterials: String,
    @DatabaseField
    val imgUrl: String? = null,
    @DatabaseField
    val tags: String,
    @DatabaseField
    val courseId: Long,
    @DatabaseField
    val startTimestampSec: Long,
    @DatabaseField
    val endTimestampSec: Long,
    @DatabaseField
    val isFavourite: Boolean
) {
    constructor() : this(
        title = "",
        additionalMaterials = "",
        tags = "",
        courseId = 0,
        startTimestampSec = 0,
        endTimestampSec = 0,
        isFavourite = false
    )
}
