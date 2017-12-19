package com.sunayashivakumar.github.view;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sunayashivakumar.github.R;
import com.sunayashivakumar.github.controller.GitClient;
import com.sunayashivakumar.github.controller.GitFollower;
import com.sunayashivakumar.github.model.FollowersConnect;
import com.sunayashivakumar.github.model.UserFollower;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * FollowersActivity class for the followers view.
 *
 * @author Sunaya Shivakumar
 */
public class FollowersActivity extends AppCompatActivity {
    /** the textview for the username */
    private TextView userName;

    /** the recyclerView */
    private RecyclerView recyclerView;

    /** the list of UserFollower objects */
    private List<UserFollower> followersList = new ArrayList<>();

    /** the adapter */
    private RecyclerView.Adapter adapter;

    /** the input string */
    String input;

    /**
     * overridden onCreate method that gets all the TextViews and RecyclerViews.
     * This method all calls the method consumeFollowers that consumes the data.
     *
     * @param savedInstanceState the input savedInstanceState.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        input = getIntent().getExtras().getString("user_for_followers");

        userName = (TextView) findViewById(R.id.FollowersUser);
        userName.setText(input);

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerFollowers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FollowersConnect(followersList, getApplicationContext(), R.layout.item_followers);
        recyclerView.setAdapter(adapter);
        consumeFollowers();
    }

    /**
     * consumeFollowers method that sets all the data read from the json input.
     */
    public void consumeFollowers() {
        GitFollower apiClient = GitClient.getClient().create(GitFollower.class);
        Call<List<UserFollower>> call = apiClient.getUserFollowers(input);
        call.enqueue(new Callback<List<UserFollower>>() {
            @Override
            public void onResponse(Call<List<UserFollower>> call, Response<List<UserFollower>> response) {
                followersList.clear();
                followersList.addAll(response.body());
                adapter.notifyDataSetChanged();

                SharedPreferences preferences = getSharedPreferences("followers_" + input, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                for (int i = 0; i < followersList.size(); i++) {
                    editor.putString("follower" + i, followersList.get(i).getUserName());
                }
                editor.apply();
            }

            @Override
            public void onFailure(Call<List<UserFollower>> call, Throwable t) {
                System.out.println("Unable to consume data" + t.toString());
            }
        });
    }
}
