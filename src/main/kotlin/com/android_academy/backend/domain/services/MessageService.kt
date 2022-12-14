package com.android_academy.backend.domain.services

import com.android_academy.backend.db.models.LessonEntity
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.springframework.beans.factory.annotation.Autowired

class MessageService(
    @Autowired val messenger: FirebaseMessaging
) {
    fun sendNotifications(lesson: LessonEntity, fcmTokens: List<String?>) {
        for (fcmToken in fcmTokens) {
            messenger.send(
                Message.builder()
                    .setToken(fcmToken)
                    .setNotification(
                        Notification.builder()
                            .setTitle(lesson.title)
                            .build()
                    )
                    .putData(
                        "click_action",
                        "https://www.bestapp/show?courseId=${lesson.courseId}&lessonId=${lesson.id}"
                    )
                    .build()

            )
        }
    }
}
