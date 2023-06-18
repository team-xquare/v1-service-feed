package com.xquare.v1servicefeed.notification.extension;

import com.xquare.v1servicefeed.feed.CategoryEnum;
import com.xquare.v1servicefeed.feed.spi.CategorySpi;
import com.xquare.v1servicefeed.notification.NotificationSpi;

public class CommentNotificationUtilImpl extends NotificationUtil {
    private static final String COMMENT = "COMMENT";
    private static final String CONTENT = "댓글이 달렸습니다.";

    public CommentNotificationUtilImpl(NotificationSpi notificationSpi, CategorySpi categorySpi) {
        super(notificationSpi, categorySpi);
    }

    protected String getTopic() {
        return COMMENT;
    }

    protected String getContent() {
        return CONTENT;
    }


}
