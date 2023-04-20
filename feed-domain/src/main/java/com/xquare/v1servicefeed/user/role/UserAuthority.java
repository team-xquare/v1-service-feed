package com.xquare.v1servicefeed.user.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserAuthority {
    STD("학생생활안전부", ""), //student department
    STC("학생회", "https://xquare.s3.ap-northeast-2.amazonaws.com/%E1%84%92%E1%85%A1%E1%86%A8%E1%84%89%E1%85%A2%E1%86%BC%E1%84%92%E1%85%AC.png"), //student council
    STA("자치회", ""), //student autonomy
    DOD("사감부", "https://xquare.s3.ap-northeast-2.amazonaws.com/%E1%84%89%E1%85%A1%E1%84%80%E1%85%A1%E1%86%B7%E1%84%91%E1%85%B3%E1%84%89%E1%85%A1.png"), //dormitory department
    DOS("사감선생님", "https://xquare.s3.ap-northeast-2.amazonaws.com/%E1%84%89%E1%85%A1%E1%84%80%E1%85%A1%E1%86%B7%E1%84%91%E1%85%B3%E1%84%89%E1%85%A1.png"), //dormitory supervisor
    CLL("동아리장", ""), //club leader
    OST("학생", ""), //ordinary student
    UKN("익명", ""), //unknown
    TEST("테스트", ""), //test
    LIB("도서부", "https://xquare.s3.ap-northeast-2.amazonaws.com/25885796-2495-4e23-93c0-3330fe3ed796%40IMG_1833.JPG"), // library department
    ADMIN("관리자", "https://xquare.s3.ap-northeast-2.amazonaws.com/%E1%84%89%E1%85%A1%E1%84%80%E1%85%A1%E1%86%B7%E1%84%91%E1%85%B3%E1%84%89%E1%85%A1.png"),
    ;

    private final String name;
    private final String profile;
}
