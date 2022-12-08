package com.xquare.v1servicefeed.user.spi;

import com.xquare.v1servicefeed.user.User;

import java.util.List;
import java.util.UUID;

public interface CommentUserSpi {

    List<User> queryUserByIds(List<UUID> ids);
}
