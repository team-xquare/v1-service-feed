package com.xquare.v1servicefeed.feedlike.event;

import com.xquare.v1servicefeed.feedlike.api.dto.event.SaveFeedLikeEvent;
import com.xquare.v1servicefeed.notification.NotificationSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FeedLikeEventListener {

    private final NotificationSpi notificationSpi;
    private static final String TOPIC = "FEED_LIKE";
    private static final String CONTENT = "좋아요가 달렸습니다.";

    @EventListener
    public void onSaveFeedLike(SaveFeedLikeEvent event) {
        notificationSpi.sendNotification(
                event.getFeedUserId(),
                TOPIC,
                CONTENT,
                event.getFeedId().toString()
        );
    }
}
