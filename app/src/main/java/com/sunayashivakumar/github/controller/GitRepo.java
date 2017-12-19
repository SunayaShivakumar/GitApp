package com.sunayashivakumar.github.controller;

import com.sunayashivakumar.github.model.UserRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * GitRepo to make calls to GitHub Api to access
 * a given user's public repositories.
 *
 * @author Sunaya Shivakumar
 */
public interface GitRepo {
    /**
     * Get calls to access a user's public repositories.
     *
     * @param user the given user's name.
     * @return a list of UserRepo objects.
     */
    @GET("/users/{user}/repos")
    Call<List<UserRepo>> getUserRepos(@Path("user") String user);
}
