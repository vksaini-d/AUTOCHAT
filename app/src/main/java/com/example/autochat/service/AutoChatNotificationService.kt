package com.example.autochat.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AutoChatNotificationService : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        // TODO: Implement filtering and reply logic
        Log.d("AutoChat", "Notification received from: ${sbn?.packageName}")
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
    }
}
