package com.android_academy.backend.db.models

import com.android_academy.backend.api.models.UserProfileDTO
import com.android_academy.backend.domain.models.UserRole
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable
class UserEntity(
    @DatabaseField(generatedId = true)
    val id: Long = 0,
    @DatabaseField
    val username: String,
    @DatabaseField
    val pwd: String,
    @DatabaseField
    val name: String,
    @DatabaseField
    val userRole: UserRole
) {
    constructor() : this(id = 0, username = "", pwd = "", name = "", userRole = UserRole.UNKNOWN)
}

fun UserEntity.toUserProfileDTO(): UserProfileDTO =
    UserProfileDTO(
        username = username,
        name = name,
        userRole = userRole
    )
