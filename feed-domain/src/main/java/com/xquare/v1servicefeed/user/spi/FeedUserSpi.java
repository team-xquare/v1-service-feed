package com.xquare.v1servicefeed.user.spi;

import com.xquare.v1servicefeed.annotation.Spi;
import com.xquare.v1servicefeed.user.User;
import com.xquare.v1servicefeed.user.role.UserRole;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Spi
public interface FeedUserSpi {
    List<User> queryUserByIds(List<UUID> ids);
    void validateUserId(UUID userId, UUID currentUserId);
    List<User> queryAllUserByRole(String role);
    Optional<User> queryUserById(UUID id);
}
