package com.xquare.v1servicefeed.user;

import com.xquare.v1servicefeed.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
@Aggregate
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
