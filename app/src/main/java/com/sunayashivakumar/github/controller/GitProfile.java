package com.sunayashivakumar.github.controller;

import com.sunayashivakumar.github.model.UserProfile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * GitProfile to make calls to GitHub API to access
 * a given user's profile.
 *
 * @author Sunaya Shivakumar
 */
public interface GitProfile {
    /**
     * Get calls to access the given user's profile info.
     *
     * @param user the given user's name.
     * @return a UserProfile object.
     */
    @GET("/users/{user}")
    Call<UserProfile> getUserProfile(@Path("user") String user);

    /**
     * Get calls to access the given user's profile info,
     * after signing in. So it uses the access token.
     *
     * @param access_token the given user's access token.
     * @return a UserProfile object.
     */
    @GET("/user")
    Call<UserProfile> getLoggedInUserProfile(@Query("access_token") String access_token);
}
