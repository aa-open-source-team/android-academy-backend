package com.android_academy.backend.db.models

import com.android_academy.backend.domain.models.CourseLanguage
import com.android_academy.backend.domain.models.CourseMode
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "courses")
class CourseEntity(
    @DatabaseField(generatedId = true)
    val id: Long? = null,
    @DatabaseField
    val title: String,
    @DatabaseField
    val shortDescription: String? = null,
    @DatabaseField
    val fullDescription: String? = null,
    @DatabaseField
    val imgUrl: String? = null,
    @DatabaseField
    val tags: String? = null,
    @DatabaseField
    val isSubscribed: Boolean,
    @DatabaseField
    val language: CourseLanguage,
    @DatabaseField
    val startTimestampSec: Long,
    @DatabaseField
    val endTimestampSec: Long,
    @DatabaseField
    val hostCities: String,
    @DatabaseField
    val mode: CourseMode,
) {
    constructor() : this(
        title = "",
        isSubscribed = false,
        language = CourseLanguage.ENGLISH,
        startTimestampSec = 0,
        endTimestampSec = 0,
        hostCities = "",
        mode = CourseMode.OFFLINE
    )
}
