package com.xquare.v1servicefeed.notification;

import java.util.UUID;

public interface NotificationSpi {
    void sendNotification(UUID userId, String topic, String content, String threadId);
    void sendGroupNotification(String topic, String content, String threadId);
}
