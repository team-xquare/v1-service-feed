package com.xquare.v1servicefeed.feed.event;

import com.xquare.v1servicefeed.feed.api.dto.event.SaveFeedEvent;
import com.xquare.v1servicefeed.notification.NotificationSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FeedEventListener {

    private final NotificationSpi notificationSpi;
    private static final String FEED_NOTICE = "FEED_NOTICE";
    private static final String CONTENT = "새로운 공지가 등록되었습니다.";

    @EventListener
    public void onSaveFeed(SaveFeedEvent event) {
        if ("DOS".equals(event.getType()) || "ADMIN".equals(event.getType())) {
            notificationSpi.sendGroupNotification(
                    FEED_NOTICE,
                    CONTENT,
                    FEED_NOTICE
            );
        }
    }
}
