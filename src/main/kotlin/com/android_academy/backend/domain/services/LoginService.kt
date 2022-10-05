package com.android_academy.backend.domain.services

import com.android_academy.backend.db.dao.AuthInfoDao
import com.android_academy.backend.db.dao.UsersDao
import com.android_academy.backend.db.models.AuthInfoEntity
import com.android_academy.backend.domain.models.LoginResult
import org.springframework.beans.factory.annotation.Autowired

class LoginService(
    @Autowired val userDAO: UsersDao,
    @Autowired val authInfoDao: AuthInfoDao
) {
    fun login(username: String, pwd: String): LoginResult {
        val user = userDAO.findBy(username = username)
        val success = user?.pwd == pwd
        var token = ""
        if (success) {
            token = generateToken()
            authInfoDao.save(
                AuthInfoEntity(token = token, userId = user!!.id)
            )
        }
        return LoginResult(
            success = success,
            token = token,
            user = user
        )
    }

    fun getAuthInfo(token: String): AuthInfoEntity? =
        authInfoDao.findBy(token = token)

    fun updateAuthInfo(token: String, fcmToken: String, userId: Long) {
        authInfoDao.save(
            AuthInfoEntity(
                token = token,
                fcmToken = fcmToken,
                userId = userId
            )
        )
    }
}
