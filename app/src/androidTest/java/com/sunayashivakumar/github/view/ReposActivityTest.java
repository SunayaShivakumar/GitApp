package com.sunayashivakumar.github.view;

import android.support.test.runner.AndroidJUnit4;

import com.sunayashivakumar.github.controller.GitClient;
import com.sunayashivakumar.github.controller.GitRepo;
import com.sunayashivakumar.github.model.UserRepo;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;

/**
 * Unit testing api response for repos
 *
 * @author Sunaya Shivakumar
 */
@RunWith(AndroidJUnit4.class)
public class ReposActivityTest {
    /**
     * unit test the number of repositories
     *
     * @throws Exception on failure
     */
    @Test
    public void testReposCount() throws Exception {
        GitRepo apiClient = GitClient.getClient().create(GitRepo.class);
        Call<List<UserRepo>> call;

        call = apiClient.getUserRepos("SunayaShivakumar");
        call.enqueue(new Callback<List<UserRepo>>() {
            @Override
            public void onResponse(Call<List<UserRepo>> call, Response<List<UserRepo>> response) {
                assertEquals("8", response.body().size());
            }

            @Override
            public void onFailure(Call<List<UserRepo>> call, Throwable t) {
                System.out.println("Unable to consume data" + t.toString());
            }
        });
    }
}