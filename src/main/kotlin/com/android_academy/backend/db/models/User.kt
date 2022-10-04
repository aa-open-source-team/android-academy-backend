package com.android_academy.backend.db.models

import com.android_academy.backend.api.models.UserProfileDTO
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable
class User(
    @DatabaseField(generatedId = true)
    val id: Long = 0,
    @DatabaseField
    val username: String,
    @DatabaseField
    val pwd: String,
    @DatabaseField
    val name: String,
    @DatabaseField
    val mentor: Boolean
) {
    constructor() : this(id = 0, username = "", pwd = "", name = "", mentor = false)
}

fun User.toUserProfileDTO(): UserProfileDTO =
    UserProfileDTO(
        username = username,
        name = name,
        mentor = mentor
    )
