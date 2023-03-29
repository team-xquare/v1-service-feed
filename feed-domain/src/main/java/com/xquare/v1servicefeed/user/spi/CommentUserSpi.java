package com.xquare.v1servicefeed.user.spi;

import com.xquare.v1servicefeed.user.User;
import com.xquare.v1servicefeed.user.role.UserRole;

import java.util.List;
import java.util.UUID;

public interface CommentUserSpi {
    List<User> queryUserByIds(List<UUID> ids);
    void validateUserId(UUID userId, UUID currentUserId);
    List<User> queryAllUserByRole(String role);
}
