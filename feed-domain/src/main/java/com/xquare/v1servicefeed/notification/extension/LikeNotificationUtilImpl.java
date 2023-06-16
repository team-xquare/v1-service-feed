package com.xquare.v1servicefeed.notification.extension;

import com.xquare.v1servicefeed.feed.CategoryEnum;
import com.xquare.v1servicefeed.feed.spi.CategorySpi;
import com.xquare.v1servicefeed.notification.NotificationSpi;

public class LikeNotificationUtilImpl extends NotificationUtil {
    private static final String FEED_NOTICE_LIKE = "FEED_NOTICE_LIKE";
    private static final String FEED_BAMBOO_LIKE = "FEED_BAMBOO_LIKE";
    private static final String CONTENT = "좋아요가 달렸습니다.";

    public LikeNotificationUtilImpl(NotificationSpi notificationSpi, CategorySpi categorySpi) {
        super(notificationSpi, categorySpi);
    }

    protected String getTopic(String feedCategoryName) {
        if (CategoryEnum.NOTICE.getName().equals(feedCategoryName)) {
            return FEED_NOTICE_LIKE;
        } else {
            return FEED_BAMBOO_LIKE;
        }
    }

    protected String getContent() {
        return CONTENT;
    }
}
