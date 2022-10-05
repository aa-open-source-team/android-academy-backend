package com.android_academy.backend.db.dao

import com.android_academy.backend.db.models.CourseEntity
import com.android_academy.backend.db.models.UsersCoursesConnectingTable
import com.j256.ormlite.dao.Dao

class CoursesDao(
    private val coursesDelegateDAO: Dao<CourseEntity, Long>,
    private val userCoursesDelegateDao: Dao<UsersCoursesConnectingTable, Long>
) {
    fun save(userId: Long, course: CourseEntity): CourseEntity {
        if (course.id != null) {
            coursesDelegateDAO.update(course)
        } else {
            coursesDelegateDAO.create(course)
        }
        if (course.isSubscribed) {
            userCoursesDelegateDao.create(
                UsersCoursesConnectingTable(
                    userId = userId, courseId = course.id!!
                )
            )
        }
        return findById(course.id!!)
    }

    private fun findById(id: Long): CourseEntity =
        coursesDelegateDAO.queryForId(id)

    fun getAll(): List<CourseEntity> =
        coursesDelegateDAO.queryForAll()

    fun getFavorite(userId: Long): List<CourseEntity> {
        val courseIds = userCoursesDelegateDao.queryForEq("userId", userId)
            .map { usersCourses -> usersCourses.courseId }
        return coursesDelegateDAO.queryBuilder()
            .where()
            .`in`("id", courseIds)
            .and()
            .eq("subscribed", true)
            .query()
    }
}
