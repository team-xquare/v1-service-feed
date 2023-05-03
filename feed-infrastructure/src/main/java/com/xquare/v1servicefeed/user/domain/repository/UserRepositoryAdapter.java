package com.xquare.v1servicefeed.user.domain.repository;

import com.xquare.v1servicefeed.feign.client.UserClient;
import com.xquare.v1servicefeed.feign.client.dto.response.UserInfoElement;
import com.xquare.v1servicefeed.user.User;
import com.xquare.v1servicefeed.user.exception.ForbiddenUserException;
import com.xquare.v1servicefeed.user.spi.CommentUserSpi;
import com.xquare.v1servicefeed.user.spi.FeedUserSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserRepositoryAdapter implements FeedUserSpi, CommentUserSpi {

    private final UserClient userClient;

    @Override
    public List<User> queryUserByIds(List<UUID> ids) {
        if (ids.isEmpty()) {
            return List.of();
        }
        try {
            List<UserInfoElement> userList = userClient.getUserInfo(ids).getUsers();

            return userList.stream()
                    .map(userInfoElement -> User.builder()
                            .id(userInfoElement.getId())
                            .accountId(userInfoElement.getAccountId())
                            .name(userInfoElement.getName())
                            .password(userInfoElement.getPassword())
                            .birthDay(userInfoElement.getBirthDay())
                            .grade(userInfoElement.getGrade())
                            .classNum(userInfoElement.getClassNum())
                            .num(userInfoElement.getNum())
                            .profileFileName(userInfoElement.getProfileFileName())
                            .build())
                    .toList();
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public void validateUserId(UUID userId, UUID currentUserId) {
        if (!currentUserId.equals(userId)) {
            throw ForbiddenUserException.EXCEPTION;
        }
    }

    @Override
    public List<User> queryAllUserByRole(String role) {
        List<UserInfoElement> userList = userClient.getStudent(role).getUsers();

        return userList.stream()
                .map(userInfoElement -> User.builder()
                        .id(userInfoElement.getId())
                        .accountId(userInfoElement.getAccountId())
                        .name(userInfoElement.getName())
                        .password(userInfoElement.getPassword())
                        .birthDay(userInfoElement.getBirthDay())
                        .grade(userInfoElement.getGrade())
                        .classNum(userInfoElement.getClassNum())
                        .num(userInfoElement.getNum())
                        .profileFileName(userInfoElement.getProfileFileName())
                        .build())
                .toList();
    }
}
