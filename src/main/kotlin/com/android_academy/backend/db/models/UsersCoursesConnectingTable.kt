package com.android_academy.backend.db.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable
class UsersCoursesConnectingTable(
    @DatabaseField(generatedId = true)
    val id: Long? = null,
    @DatabaseField
    val userId: Long,
    @DatabaseField
    val courseId: Long
) {
    constructor() : this(userId = 0, courseId = 0)
}
