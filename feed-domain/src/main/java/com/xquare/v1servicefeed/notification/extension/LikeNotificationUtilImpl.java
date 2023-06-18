package com.xquare.v1servicefeed.notification.extension;

import com.xquare.v1servicefeed.feed.CategoryEnum;
import com.xquare.v1servicefeed.feed.spi.CategorySpi;
import com.xquare.v1servicefeed.notification.NotificationSpi;

public class LikeNotificationUtilImpl extends NotificationUtil {
    private static final String LIKE = "LIKE";
    private static final String CONTENT = "좋아요가 달렸습니다.";

    public LikeNotificationUtilImpl(NotificationSpi notificationSpi, CategorySpi categorySpi) {
        super(notificationSpi, categorySpi);
    }

    protected String getTopic() {
        return LIKE;
    }

    protected String getContent() {
        return CONTENT;
    }
}
