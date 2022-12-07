package com.xquare.v1servicefeed.feign.client.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class UserInfoElement {

    private final UUID id;
    private final String accountId;
    private final String password;
    private final String name;
    private final Integer grade;
    private final Integer classNum;
    private final Integer num;
    private final LocalDate birthDay;
    private final String profileFileName;
}
