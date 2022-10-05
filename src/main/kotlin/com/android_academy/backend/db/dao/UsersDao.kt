package com.android_academy.backend.db.dao

import com.android_academy.backend.db.models.UserEntity
import com.j256.ormlite.dao.Dao

class UsersDao(
    private val delegateDAO: Dao<UserEntity, Long>
) {
    fun findBy(username: String): UserEntity? =
        delegateDAO.queryForEq(
            "username", username
        ).let { result ->
            if (result.isNotEmpty()) {
                result.first()
            } else {
                null
            }
        }

    fun save(user: UserEntity) {
        delegateDAO.create(user)
    }
}
