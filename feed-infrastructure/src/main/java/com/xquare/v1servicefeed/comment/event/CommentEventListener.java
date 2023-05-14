package com.xquare.v1servicefeed.comment.event;

import com.xquare.v1servicefeed.comment.api.dto.event.SaveCommentEvent;
import com.xquare.v1servicefeed.notification.NotificationSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentEventListener {

    private final NotificationSpi notificationSpi;
    private static final String FEED_COMMENT = "FEED_COMMENT";
    private static final String CONTENT = "댓글이 달렸습니다.";

    @EventListener
    public void onSaveComment(SaveCommentEvent event) {
        notificationSpi.sendNotification(
                event.getFeedUserId(),
                FEED_COMMENT,
                CONTENT,
                event.getFeedId().toString()
        );
    }
}
