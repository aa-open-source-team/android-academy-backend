package com.android_academy.backend.domain.services

import com.android_academy.backend.db.dao.AuthInfoDao
import com.android_academy.backend.db.dao.UsersDao
import com.android_academy.backend.db.exceptions.ExistingEntityException
import com.android_academy.backend.db.models.AuthInfoEntity
import com.android_academy.backend.db.models.UserEntity
import com.android_academy.backend.domain.models.LoginResult
import org.springframework.beans.factory.annotation.Autowired

class RegisterService(
    @Autowired val userDAO: UsersDao,
    @Autowired val authInfoDao: AuthInfoDao
) {
    fun register(username: String, pwd: String, name: String, mentor: Boolean): LoginResult {
        val existingUser = userDAO.findBy(username = username)
        if (existingUser == null) {
            val user = UserEntity(username = username, pwd = pwd, name = name, mentor = mentor)
            userDAO.save(user = user)
            val token = generateToken()
            authInfoDao.save(authInfo = AuthInfoEntity(token = token, userId = user.id))
            return LoginResult(
                success = true,
                token = token,
                user = user
            )
        } else {
            throw ExistingEntityException()
        }
    }
}
