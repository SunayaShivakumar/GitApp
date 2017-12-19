package com.sunayashivakumar.github.view;

import android.support.test.runner.AndroidJUnit4;

import com.sunayashivakumar.github.controller.GitClient;
import com.sunayashivakumar.github.controller.GitFollower;
import com.sunayashivakumar.github.controller.GitFollowing;
import com.sunayashivakumar.github.model.UserFollower;
import com.sunayashivakumar.github.model.UserFollowing;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;

/**
 * Unit testing api response for following
 *
 * @author Sunaya Shivakumar
 */
@RunWith(AndroidJUnit4.class)
public class FollowingActivityTest {
    /**
     * unit test the number of following
     *
     * @throws Exception on failure
     */
    @Test
    public void testFollowingCount() throws Exception {
        GitFollowing apiClient = GitClient.getClient().create(GitFollowing.class);
        Call<List<UserFollowing>> call;

        call = apiClient.getUserFollowing("SunayaShivakumar");
        call.enqueue(new Callback<List<UserFollowing>>() {
            @Override
            public void onResponse(Call<List<UserFollowing>> call, Response<List<UserFollowing>> response) {
                assertEquals("3", response.body().size());
            }

            @Override
            public void onFailure(Call<List<UserFollowing>> call, Throwable t) {
                System.out.println("Unable to consume data" + t.toString());
            }
        });
    }
}