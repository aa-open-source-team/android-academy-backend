package com.android_academy.backend.services

import com.android_academy.backend.db.dao.AuthInfoDao
import com.android_academy.backend.db.dao.LessonsDao
import com.android_academy.backend.db.dao.UsersCoursesDao
import com.android_academy.backend.db.models.Lesson
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.TimeUnit

class LessonsService(
    @Autowired val lessonsDao: LessonsDao,
    @Autowired val usersCoursesDao: UsersCoursesDao,
    @Autowired val authInfoDao: AuthInfoDao
) {
    fun save(lesson: Lesson): Lesson {
        lessonsDao.save(lesson)
        return lesson
    }

    fun getByCourseId(id: Long): List<Lesson> =
        lessonsDao.findByCourseId(id)

    fun getById(id: Long): Lesson? =
        lessonsDao.findById(id)

    fun getAll(): List<Lesson> =
        lessonsDao.getAll()

    fun findFcmTokensToBeNotified(): Map<Lesson, List<String?>> {
        val lessonToTokens = HashMap<Lesson, List<String?>>()
        for (lesson in getAll()) {
            val timeDiff = lesson.startTimestampSec - System.currentTimeMillis()
            if (timeDiff > 0 && timeDiff < TimeUnit.MILLISECONDS.convert(15, TimeUnit.MINUTES)) {
                val userIds = usersCoursesDao.findUserIds(courseId = lesson.courseId)
                lessonToTokens[lesson] = authInfoDao.findFcmTokens(userIds)
            }
        }
        return lessonToTokens
    }
}
