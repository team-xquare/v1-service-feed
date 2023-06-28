package com.xquare.v1servicefeed.notification.extension;

import com.xquare.v1servicefeed.notification.NotificationSpi;
import org.springframework.stereotype.Component;

@Component
public class LikeNotificationUtilImpl extends NotificationUtil {
    private static final String CONTENT = "좋아요가 달렸습니다.";

    public LikeNotificationUtilImpl(NotificationSpi notificationSpi) {
        super(notificationSpi);
    }

    protected String getContent() {
        return CONTENT;
    }
}
