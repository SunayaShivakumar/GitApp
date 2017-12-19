package com.sunayashivakumar.github.view;

import android.support.test.runner.AndroidJUnit4;

import com.sunayashivakumar.github.controller.GitClient;
import com.sunayashivakumar.github.controller.GitFollower;
import com.sunayashivakumar.github.controller.GitRepo;
import com.sunayashivakumar.github.model.UserFollower;
import com.sunayashivakumar.github.model.UserRepo;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;

/**
 * Unit testing api response for followers
 *
 * @author Sunaya Shivakumar
 */
@RunWith(AndroidJUnit4.class)
public class FollowersActivityTest {
    /**
     * unit test the number of followers.
     *
     * @throws Exception on failure
     */
    @Test
    public void testFollowersCount() throws Exception {
        GitFollower apiClient = GitClient.getClient().create(GitFollower.class);
        Call<List<UserFollower>> call;

        call = apiClient.getUserFollowers("SunayaShivakumar");
        call.enqueue(new Callback<List<UserFollower>>() {
            @Override
            public void onResponse(Call<List<UserFollower>> call, Response<List<UserFollower>> response) {
                assertEquals("9", response.body().size());
            }

            @Override
            public void onFailure(Call<List<UserFollower>> call, Throwable t) {
                System.out.println("Unable to consume data" + t.toString());
            }
        });
    }
}