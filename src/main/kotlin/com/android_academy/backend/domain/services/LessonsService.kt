package com.android_academy.backend.domain.services

import com.android_academy.backend.db.dao.AuthInfoDao
import com.android_academy.backend.db.dao.LessonsDao
import com.android_academy.backend.db.dao.UsersCoursesDao
import com.android_academy.backend.db.models.LessonEntity
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.TimeUnit

class LessonsService(
    @Autowired val lessonsDao: LessonsDao,
    @Autowired val usersCoursesDao: UsersCoursesDao,
    @Autowired val authInfoDao: AuthInfoDao
) {
    fun save(lesson: LessonEntity): LessonEntity {
        lessonsDao.save(lesson)
        return lesson
    }

    fun getByCourseId(id: Long): List<LessonEntity> =
        lessonsDao.findByCourseId(id)

    fun getById(id: Long): LessonEntity? =
        lessonsDao.findById(id)

    fun getAll(): List<LessonEntity> =
        lessonsDao.getAll()

    fun findFcmTokensToBeNotified(): Map<LessonEntity, List<String?>> {
        val lessonToTokens = HashMap<LessonEntity, List<String?>>()
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
