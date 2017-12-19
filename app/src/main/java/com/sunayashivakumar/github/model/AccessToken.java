package com.sunayashivakumar.github.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunayashivakumar on 10/28/17.
 */

public class AccessToken {
    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
