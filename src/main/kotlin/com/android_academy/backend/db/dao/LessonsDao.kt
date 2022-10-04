package com.android_academy.backend.db.dao

import com.android_academy.backend.db.models.Lesson
import com.j256.ormlite.dao.Dao

class LessonsDao(
    private val delegateDao: Dao<Lesson, Long>
) {
    fun save(lesson: Lesson): Lesson {
        if (lesson.id != null) {
            delegateDao.update(lesson)
        } else {
            delegateDao.create(lesson)
        }
        return findById(id = lesson.id!!)!!
    }

    fun findByCourseId(id: Long): List<Lesson> =
        delegateDao.queryForEq("course_id", id)

    fun findById(id: Long): Lesson? =
        delegateDao.queryForId(id)

    fun getAll(): List<Lesson> =
        delegateDao.queryForAll()
}
