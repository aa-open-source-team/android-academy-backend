package com.android_academy.backend.db.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "auth_info")
class AuthInfoEntity(
    @DatabaseField(id = true, columnName = "refresh_token")
    val refreshToken: String,
    @DatabaseField(columnName = COLUMN_AUTH_TOKEN)
    val authToken: String,
    @DatabaseField(columnName = "timestamp_updated")
    val timestampUpdated: Long,
    @DatabaseField(columnName = "fcm_token")
    val fcmToken: String? = null,
    @DatabaseField(columnName = "user_id")
    val userId: Long
) {
    constructor() : this(refreshToken = "", authToken = "", timestampUpdated = 0, userId = 0)

    companion object {
        const val COLUMN_AUTH_TOKEN = "auth_token"
    }
}
