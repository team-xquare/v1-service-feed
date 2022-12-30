package com.xquare.v1servicefeed.feed.spi;

import com.xquare.v1servicefeed.annotation.Spi;
import com.xquare.v1servicefeed.feed.FeedImage;

import java.util.List;
import java.util.UUID;

@Spi
public interface CommandFeedImageSpi {
    void saveAllFeedImage(List<FeedImage> feedImage);

    void deleteAllFeedImage(UUID feedId);
}
