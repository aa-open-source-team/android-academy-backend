package com.android_academy.backend.api.controllers

import com.android_academy.backend.api.models.AuthTokenDTO
import com.android_academy.backend.api.models.LoginRequestDTO
import com.android_academy.backend.api.models.LoginResponseDTO
import com.android_academy.backend.db.models.toUserProfileDTO
import com.android_academy.backend.domain.services.LoginService
import com.android_academy.backend.domain.services.generateToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/login")
class LoginController(
    @Autowired val loginService: LoginService
) {
    @PostMapping
    fun login(
        @RequestBody loginRequest: LoginRequestDTO
    ): LoginResponseDTO {
        val loginResult = loginService.login(username = loginRequest.username, pwd = loginRequest.pwd)
        if (loginResult.success) {
            return LoginResponseDTO(
                userProfile = loginResult.user!!.toUserProfileDTO(),
                refreshToken = loginResult.refreshToken
            )
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }

    @GetMapping("new-token")
    fun getNewToken(
        @RequestParam refreshToken: String
    ): AuthTokenDTO {
        val authInfo = loginService.getAuthInfoByRefreshToken(refreshToken = refreshToken)
        if (authInfo != null) {
            val newAuthToken = generateToken()
            val updateSucceeded = loginService.updateAuthInfo(
                refreshToken = refreshToken,
                authToken = newAuthToken,
                fcmToken = authInfo.fcmToken,
                userId = authInfo.userId
            )
            if (updateSucceeded) {
                return AuthTokenDTO(authToken = newAuthToken)
            } else {
                throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }
}
