package com.sunayashivakumar.github.controller;

import com.sunayashivakumar.github.model.AccessToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * GitLogin to make calls to GitHub API to access
 * the sign in web flow
 *
 * @author Sunaya Shivakumar
 */
public interface GitLogin {
    /**
     * Post call to access the sign in web flow.
     *
     * @param clientId the given user's client id.
     * @param clientSecret the given user's client secret.
     * @param code the access code.
     * @return a UserProfile object.
     */
    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    Call<AccessToken> getToken(@Field("client_id") String clientId,
                               @Field("client_secret") String clientSecret,
                               @Field("code") String code);
}
