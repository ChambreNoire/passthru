package com.chambrenoire.passthru.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccessTokens {

    private String accountId;
    private String accessToken;
    private String renewToken;
}
