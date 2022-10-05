package com.android_academy.backend.domain.services

import com.android_academy.backend.db.dao.CoursesDao
import com.android_academy.backend.db.models.CourseEntity
import org.springframework.beans.factory.annotation.Autowired

class CoursesService(
    @Autowired val coursesDao: CoursesDao,
) {
    fun save(userId: Long, course: CourseEntity): CourseEntity =
        coursesDao.save(userId = userId, course = course)

    fun getAllCourses(): List<CourseEntity> =
        coursesDao.getAll()

    fun getFavoriteCourses(userId: Long): List<CourseEntity> =
        coursesDao.getFavorite(userId = userId)
}
