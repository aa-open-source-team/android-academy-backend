package com.android_academy.backend.db.dao

import com.android_academy.backend.db.models.AuthInfoEntity
import com.android_academy.backend.db.models.AuthInfoEntity.Companion.COLUMN_AUTH_TOKEN
import com.j256.ormlite.dao.Dao

private const val SINGLE_ROW_UPDATED = 1

class AuthInfoDao(
    private val delegateDAO: Dao<AuthInfoEntity, String>
) {
    fun findByRefreshToken(refreshToken: String): AuthInfoEntity? =
        delegateDAO.queryForId(refreshToken)

    fun findBy(authToken: String): AuthInfoEntity? =
        delegateDAO.queryForEq(COLUMN_AUTH_TOKEN, authToken)
            .let { result ->
                if (result.isNotEmpty()) {
                    result.first()
                } else {
                    null
                }
            }

    fun findFcmTokens(userIds: List<Long>): List<String?> =
        delegateDAO.queryBuilder()
            .where()
            .`in`("userId", userIds)
            .query()
            .map { it.fcmToken }

    fun save(authInfo: AuthInfoEntity): Boolean =
        if (delegateDAO.queryForId(authInfo.refreshToken) != null) {
            delegateDAO.update(authInfo)
        } else {
            delegateDAO.create(authInfo)
        } == SINGLE_ROW_UPDATED
}
