package com.android_academy.backend.domain.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

// fixme: configure another messaging mechanism
@Component
class ScheduledNotificationsService(
//        @Autowired val messageService: MessageService,
    @Autowired val lessonsService: LessonsService
) {
    //    @Scheduled(cron = "0 */15 * * * *") // hourly
    fun sendNotificationsIfNeeded() {
        for ((lesson, tokens) in lessonsService.findFcmTokensToBeNotified()) {
//            messageService.sendNotifications(lesson = lesson, fcmTokens = tokens)
        }
    }
}
