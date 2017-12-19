package com.sunayashivakumar.github.controller;

import com.sunayashivakumar.github.model.UserFollowing;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * GitFollowing to make calls to GitHub API to access
 * a given user's following.
 *
 * @author Sunaya Shivakumar
 */
public interface GitFollowing {
    /**
     * Get calls to access following.
     *
     * @param user the given user's name.
     * @return a list of UserFollowing objects.
     */
    @GET("/users/{user}/following")
    Call<List<UserFollowing>> getUserFollowing(@Path("user") String user);
}
