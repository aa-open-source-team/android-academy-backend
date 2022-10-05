package com.android_academy.backend.db.dao

import com.android_academy.backend.db.models.LessonEntity
import com.j256.ormlite.dao.Dao

class LessonsDao(
    private val delegateDao: Dao<LessonEntity, Long>
) {
    fun save(lesson: LessonEntity): LessonEntity {
        if (lesson.id != null) {
            delegateDao.update(lesson)
        } else {
            delegateDao.create(lesson)
        }
        return findById(id = lesson.id!!)!!
    }

    fun findByCourseId(id: Long): List<LessonEntity> =
        delegateDao.queryForEq("course_id", id)

    fun findById(id: Long): LessonEntity? =
        delegateDao.queryForId(id)

    fun getAll(): List<LessonEntity> =
        delegateDao.queryForAll()
}
