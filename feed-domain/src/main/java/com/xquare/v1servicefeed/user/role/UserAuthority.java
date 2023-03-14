package com.xquare.v1servicefeed.user.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserAuthority {
    STD("학생생활안전부", ""), //student department
    STC("학생회", ""), //student council
    STA("자치회", ""), //student autonomy
    DOD("사감부", ""), //dormitory department
    DOS("사감선생님", ""), //dormitory supervisor
    CLL("동아리장", ""), //club leader
    OST("학생", ""), //ordinary student
    UKN("익명", ""), //unknown
    TEST("테스트", ""), //test
    ;

    private final String name;
    private final String profile;
}
