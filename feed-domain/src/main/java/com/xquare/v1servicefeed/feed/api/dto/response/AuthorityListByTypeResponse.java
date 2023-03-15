package com.xquare.v1servicefeed.feed.api.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AuthorityListByTypeResponse {
    private List<AuthorityElement> authorityList;
}
