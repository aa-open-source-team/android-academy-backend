package com.android_academy.backend.domain.services

import com.android_academy.backend.db.dao.AuthInfoDao
import com.android_academy.backend.db.dao.UsersDao
import com.android_academy.backend.db.models.AuthInfoEntity
import com.android_academy.backend.domain.models.LoginResult
import org.springframework.beans.factory.annotation.Autowired
import java.time.Clock

class LoginService(
    @Autowired val userDAO: UsersDao,
    @Autowired val authInfoDao: AuthInfoDao,
    @Autowired val clock: Clock
) {
    fun login(username: String, pwd: String): LoginResult {
        val user = userDAO.findBy(username = username)
        val success = user?.pwd == pwd
        var refreshToken = ""
        if (success) {
            refreshToken = generateToken()
            authInfoDao.save(
                AuthInfoEntity(refreshToken = refreshToken, authToken = "", timestampUpdated = 0, userId = user!!.id)
            )
        }
        return LoginResult(
            success = success,
            refreshToken = refreshToken,
            user = user
        )
    }

    fun getAuthInfoByRefreshToken(refreshToken: String): AuthInfoEntity? =
        authInfoDao.findByRefreshToken(refreshToken = refreshToken)

    fun getValidAuthInfo(authToken: String): AuthInfoEntity? {
        val authInfo = authInfoDao.findBy(authToken = authToken)
        return if (isTokenValid(authInfo)) {
            authInfo
        } else {
            null
        }
    }

    fun updateAuthInfo(refreshToken: String, authToken: String, fcmToken: String?, userId: Long): Boolean =
        authInfoDao.save(
            AuthInfoEntity(
                refreshToken = refreshToken,
                authToken = authToken,
                timestampUpdated = clock.millis(),
                fcmToken = fcmToken,
                userId = userId
            )
        )

    private fun isTokenValid(authInfo: AuthInfoEntity?): Boolean =
        authInfo != null && (clock.millis() - authInfo.timestampUpdated) <= TOKEN_LIFE_TIME

    companion object {
        private const val TOKEN_LIFE_TIME = 86400000
    }
}
