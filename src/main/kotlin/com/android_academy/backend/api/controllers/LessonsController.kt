package com.android_academy.backend.api.controllers

import com.android_academy.backend.api.models.LessonDTO
import com.android_academy.backend.api.models.UpdateLessonRequestDTO
import com.android_academy.backend.api.models.fromLesson
import com.android_academy.backend.api.models.toLesson
import com.android_academy.backend.domain.services.LessonsService
import com.android_academy.backend.domain.services.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/lessons")
class LessonsController(
    @Autowired val lessonsService: LessonsService,
    @Autowired val loginService: LoginService
) {
    @PostMapping("update")
    fun updateLesson(
        @RequestHeader(CoursesController.TOKEN_HEADER, required = false) token: String?,
        @RequestBody updateLessonRequestDTO: UpdateLessonRequestDTO
    ): LessonDTO {
        if (token == null) {
            throw throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
        val authInfo = loginService.getAuthInfo(token)
        if (authInfo != null) {
            return fromLesson(lessonsService.save(lesson = updateLessonRequestDTO.toLesson()))
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }

    @GetMapping("all")
    fun getAll(
        @RequestHeader(CoursesController.TOKEN_HEADER, required = false) token: String?,
        @RequestParam courseId: Long
    ): List<LessonDTO> {
        if (token != null && loginService.getAuthInfo(token) != null) {
            return lessonsService.getAll()
                .map { lecture -> fromLesson(lesson = lecture) }
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }

    @GetMapping("course-id")
    fun getLessonsByCourseId(
        @RequestHeader(CoursesController.TOKEN_HEADER, required = false) token: String?,
        @RequestParam courseId: Long
    ): List<LessonDTO> {
        if (token != null && loginService.getAuthInfo(token) != null) {
            return lessonsService.getByCourseId(id = courseId)
                .map { lesson -> fromLesson(lesson) }
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }

    @GetMapping("lesson-id")
    fun getLessonById(
        @RequestHeader(CoursesController.TOKEN_HEADER, required = false) token: String?,
        @RequestParam lessonId: Long
    ): LessonDTO {
        if (token != null && loginService.getAuthInfo(token) != null) {
            val lesson = lessonsService.getById(id = lessonId)
            if (lesson != null) {
                return fromLesson(lesson)
            } else {
                throw ResponseStatusException(HttpStatus.NOT_FOUND)
            }
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }
}
