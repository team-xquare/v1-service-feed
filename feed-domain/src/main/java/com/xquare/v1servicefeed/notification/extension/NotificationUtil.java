package com.xquare.v1servicefeed.notification.extension;

import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.spi.CategorySpi;
import com.xquare.v1servicefeed.notification.NotificationSpi;

public abstract class NotificationUtil {

    NotificationSpi notificationSpi;
    CategorySpi categorySpi;

    public void sendNotification(Feed feed) {
        String feedCategoryName = categorySpi.queryCategoryById(feed.getCategoryId()).getName();
        notificationSpi.sendNotification(
                feed.getUserId(),
                getTopic(feedCategoryName),
                getContent(),
                feed.getId().toString()
        );
    }

    protected abstract String getTopic(String feedCategoryName);
    protected abstract String getContent();

    protected NotificationUtil(NotificationSpi notificationSpi, CategorySpi categorySpi) {
        this.notificationSpi = notificationSpi;
        this.categorySpi = categorySpi;
    }
}
