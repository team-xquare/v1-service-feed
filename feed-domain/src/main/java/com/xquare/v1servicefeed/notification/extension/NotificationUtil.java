package com.xquare.v1servicefeed.notification.extension;

import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.notification.NotificationSpi;

public abstract class NotificationUtil {

    NotificationSpi notificationSpi;

    public void sendNotification(Feed feed, String topic) {
        notificationSpi.sendNotification(
                feed.getUserId(),
                topic,
                getContent(),
                feed.getId().toString()
        );
    }

    protected abstract String getContent();

    protected NotificationUtil(NotificationSpi notificationSpi) {
        this.notificationSpi = notificationSpi;
    }
}
