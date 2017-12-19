package com.sunayashivakumar.github.view;

import com.sunayashivakumar.github.controller.GitClient;
import com.sunayashivakumar.github.controller.GitProfile;
import com.sunayashivakumar.github.model.UserProfile;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Unit testing api response for profile
 *
 * @author Sunaya Shivakumar
 */
@RunWith(AndroidJUnit4.class)
public class ProfileActivityTest {
    /**
     * unit test the name of the user.
     *
     * @throws Exception on failure
     */
    @Test
    public void testProfileActivityName() throws Exception {
        GitProfile apiClient = GitClient.getClient().create(GitProfile.class);
        Call<UserProfile> call;

        call = apiClient.getUserProfile("SunayaShivakumar");
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                assertEquals("Sunaya Shivakumar", response.body().getFullName());
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                System.out.println("Unable to consume data" + t.toString());
            }
        });
    }

    /**
     * unit test the login of the user
     *
     * @throws Exception on failure
     */
    @Test
    public void testProfileActivityLogin() throws Exception {
        GitProfile apiClient = GitClient.getClient().create(GitProfile.class);
        Call<UserProfile> call;

        call = apiClient.getUserProfile("SunayaShivakumar");
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                assertEquals("SunayaShivakumar", response.body().getUserName());
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                System.out.println("Unable to consume data" + t.toString());
            }
        });
    }

    /**
     * unit test the repos of the user
     *
     * @throws Exception on failure
     */
    @Test
    public void testProfileActivityRepos() throws Exception {
        GitProfile apiClient = GitClient.getClient().create(GitProfile.class);
        Call<UserProfile> call;

        call = apiClient.getUserProfile("SunayaShivakumar");
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                assertEquals("8", response.body().getRepos());
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                System.out.println("Unable to consume data" + t.toString());
            }
        });
    }

    /**
     * unit test the followers of the user
     *
     * @throws Exception on failure
     */
    @Test
    public void testProfileActivityFollowers() throws Exception {
        GitProfile apiClient = GitClient.getClient().create(GitProfile.class);
        Call<UserProfile> call;

        call = apiClient.getUserProfile("SunayaShivakumar");
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                assertEquals("9", response.body().getFollowers());
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                System.out.println("Unable to consume data" + t.toString());
            }
        });
    }

    /**
     * unit test the following of the user
     *
     * @throws Exception on failure
     */
    @Test
    public void testProfileActivityFollowing() throws Exception {
        GitProfile apiClient = GitClient.getClient().create(GitProfile.class);
        Call<UserProfile> call;

        call = apiClient.getUserProfile("SunayaShivakumar");
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                assertEquals("3", response.body().getFollowing());
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                System.out.println("Unable to consume data" + t.toString());
            }
        });
    }
}