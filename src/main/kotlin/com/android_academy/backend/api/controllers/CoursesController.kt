package com.android_academy.backend.api.controllers

import com.android_academy.backend.api.models.CourseDTO
import com.android_academy.backend.api.models.fromCourse
import com.android_academy.backend.api.models.toCourse
import com.android_academy.backend.domain.services.CoursesService
import com.android_academy.backend.domain.services.LoginService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/courses")
class CoursesController(
    @Autowired val coursesService: CoursesService,
    @Autowired val loginService: LoginService
) {
    @PostMapping("update")
    fun updateCourse(
        @RequestHeader(TOKEN_HEADER, required = false) token: String?,
        @RequestBody newCourseDTO: CourseDTO
    ): CourseDTO {
        if (token != null) {
            val authInfo = loginService.getValidAuthInfo(token)
            if (authInfo != null) {
                return fromCourse(coursesService.save(userId = authInfo.userId, course = newCourseDTO.toCourse()))
            }
        }
        throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
    }


    @GetMapping("all")
    fun getAllCourses(
        @RequestHeader(TOKEN_HEADER, required = false) token: String?
    ): List<CourseDTO> {
        logger.error("The token is ${token ?: "null"}")
        if (token != null && loginService.getValidAuthInfo(token) != null) {
            return coursesService.getAllCourses()
                .map { course -> fromCourse(course = course) }
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }


    @GetMapping("favorite")
    fun getFavoriteCourses(
        @RequestHeader(TOKEN_HEADER, required = false) token: String?
    ): List<CourseDTO> {
        if (token != null) {
            val authInfo = loginService.getValidAuthInfo(token)
            if (authInfo != null) {
                return coursesService.getFavoriteCourses(userId = authInfo.userId)
                    .map { course -> fromCourse(course = course) }
            }
        }
        throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
    }

    companion object {
        const val TOKEN_HEADER = "Token"
    }
}
