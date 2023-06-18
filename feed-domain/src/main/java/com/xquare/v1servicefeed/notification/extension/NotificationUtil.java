package com.xquare.v1servicefeed.notification.extension;

import com.xquare.v1servicefeed.feed.CategoryEnum;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.spi.CategorySpi;
import com.xquare.v1servicefeed.notification.NotificationSpi;

public abstract class NotificationUtil {

    NotificationSpi notificationSpi;
    CategorySpi categorySpi;

    private String FEED_NOTICE = "FEED_NOTICE_" + getTopic();
    private String FEED_BAMBOO = "FEED_BAMBOO_" + getTopic();

    public void sendNotification(Feed feed) {
        String feedCategoryName = categorySpi.queryCategoryById(feed.getCategoryId()).getName();

        if (CategoryEnum.NOTICE.getName().equals(feedCategoryName)) {
            notificationSpi.sendNotification(
                    feed.getUserId(),
                    FEED_NOTICE,
                    getContent(),
                    feed.getId().toString()
            );
        } else {
            notificationSpi.sendNotification(
                    feed.getUserId(),
                    FEED_BAMBOO,
                    getContent(),
                    feed.getId().toString()
            );
        }

    }

    protected abstract String getTopic();
    protected abstract String getContent();

    protected NotificationUtil(NotificationSpi notificationSpi, CategorySpi categorySpi) {
        this.notificationSpi = notificationSpi;
        this.categorySpi = categorySpi;
    }
}
