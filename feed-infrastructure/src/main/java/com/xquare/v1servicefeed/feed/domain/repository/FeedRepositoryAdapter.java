package com.xquare.v1servicefeed.feed.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainUpdateFeedRequest;
import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import com.xquare.v1servicefeed.feed.domain.mapper.FeedMapper;
import com.xquare.v1servicefeed.feed.domain.repository.vo.FeedListVO;
import com.xquare.v1servicefeed.feed.domain.repository.vo.QFeedListVO;
import com.xquare.v1servicefeed.feed.exception.FeedNotFoundException;
import com.xquare.v1servicefeed.feed.spi.FeedSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.xquare.v1servicefeed.comment.domain.QCommentEntity.commentEntity;
import static com.xquare.v1servicefeed.feed.domain.QFeedEntity.feedEntity;
import static com.xquare.v1servicefeed.feedlike.domain.QFeedLikeEntity.feedLikeEntity;

@RequiredArgsConstructor
@Adapter
public class FeedRepositoryAdapter implements FeedSpi {

    private final FeedMapper feedMapper;
    private final FeedRepository feedRepository;
    private final JPAQueryFactory query;

    @Override
    @Transactional
    public void saveFeed(Feed feed) {

        feedRepository.save(feedMapper.domainToEntity(feed));
    }

    @Override
    public void deleteFeed(Feed feed) {
        feedRepository.delete(
                feedMapper.domainToEntity(feed)
        );
    }

    @Transactional
    public void updateFeed(DomainUpdateFeedRequest request) {

        FeedEntity feed = getFeedEntityById(request.getFeedId());

        feed.updateFeed(request.getContent());
    }

    @Override
    public Feed queryFeedById(UUID feedId) {
        return feedMapper.entityToDomain(getFeedEntityById(feedId));
    }

    @Override
    public List<Feed> queryAllFeedByCategory(String category) {
        List<FeedListVO> voList = query
                .select(new QFeedListVO(
                        feedEntity.id,
                        feedEntity.userId,
                        feedEntity.content,
                        feedEntity.createdAt,
                        feedLikeEntity.feed.count().intValue(),
                        commentEntity.feed.count().intValue()
                ))
                .from(feedEntity)
                .leftJoin(feedLikeEntity)
                .on(feedEntity.id.eq(feedLikeEntity.feed.id))
                .leftJoin(commentEntity)
                .on(feedEntity.id.eq(commentEntity.feed.id))
                .where(feedEntity.category.eq(category))
                .orderBy(feedEntity.createdAt.desc())
                .fetch();

        return voList.stream()
                .map(feedListVO -> Feed.builder()
                        .id(feedListVO.getFeedId())
                        .userId(feedListVO.getUserId())
                        .content(feedListVO.getContent())
                        .createdAt(feedListVO.getCreatedAt())
                        .likeCount(feedListVO.getLikeCount())
                        .commentCount(feedListVO.getCommentCount())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<UUID> queryAllFeedUserIdByCategory(String category) {
        List<FeedEntity> feedList = query
                .selectFrom(feedEntity).distinct()
                .leftJoin(feedLikeEntity)
                .on(feedEntity.id.eq(feedLikeEntity.feed.id))
                .where(feedEntity.category.eq(category))
                .orderBy(feedEntity.createdAt.desc())
                .fetch();

        return feedList.stream()
                .map(FeedEntity::getUserId)
                .toList();
    }

    public FeedEntity getFeedEntityById(UUID feedId) {
        return feedRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);
    }
}
