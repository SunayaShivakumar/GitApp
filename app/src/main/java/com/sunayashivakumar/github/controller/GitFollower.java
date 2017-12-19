package com.sunayashivakumar.github.controller;

import com.sunayashivakumar.github.model.UserFollower;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * GitFollower to make calls to GitHub API to access
 * a given user's followers.
 *
 * @author Sunaya Shivakumar
 */
public interface GitFollower {
    /**
     * Get calls to access followers.
     *
     * @param user the given user's name.
     * @return a list of UserFollower objects.
     */
    @GET("/users/{user}/followers")
    Call<List<UserFollower>> getUserFollowers(@Path("user") String user);
}
