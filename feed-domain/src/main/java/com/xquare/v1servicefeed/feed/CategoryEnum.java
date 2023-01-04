package com.xquare.v1servicefeed.feed;

import com.xquare.v1servicefeed.user.role.UserAuthority;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.xquare.v1servicefeed.user.role.UserAuthority.*;

@RequiredArgsConstructor
@Getter
public enum CategoryEnum {
    NOTICE("NOTICE", List.of(STD, STC, STA, DOD, DOS)),
    CLUB("CLUB", List.of(CLL)),
    BAMBOO("BAMBOO", List.of(STD, STC, STA, DOD, DOS, CLL, OST));

    private final String name;

    private final List<UserAuthority> authorities;
}
