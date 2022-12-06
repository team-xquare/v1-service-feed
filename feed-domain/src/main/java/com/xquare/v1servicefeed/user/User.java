package com.xquare.v1servicefeed.user;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class User {

    private final UUID id;
    private final String accountId;
    private final String name;
    private final String password;
    private final LocalDate birthDay;
    private final Integer grade;
    private final Integer classNum;
    private final Integer num;
    private final String profileFileName;
}
