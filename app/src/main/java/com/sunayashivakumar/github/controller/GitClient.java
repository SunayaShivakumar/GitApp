package com.sunayashivakumar.github.controller;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * GitClient to define base url, and set up Retrofit.
 *
 * @author Sunaya Shivakumar
 */
public class GitClient {
    /** Common endpoint for making calls to the GitHub API. */
    public static final String base_url = "https://api.github.com/";

    public static final String logged_in_url = "https://github.com/";

    /** Retrofit object to be used. */
    private static Retrofit retrofit = null;

    /**
     * getClient method that takes in the base url and connects
     * it to a Gson converter to read json output.
     *
     * @return retrofit object.
     */
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientLoggedIn() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(logged_in_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
