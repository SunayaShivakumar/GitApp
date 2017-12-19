package com.sunayashivakumar.github.view;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sunayashivakumar.github.R;
import com.sunayashivakumar.github.controller.GitClient;
import com.sunayashivakumar.github.controller.GitFollowing;
import com.sunayashivakumar.github.model.FollowingConnect;
import com.sunayashivakumar.github.model.UserFollowing;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * FollowingActivity class for the following view.
 *
 * @author Sunaya Shivakumar
 */
public class FollowingActivity extends AppCompatActivity {
    /** the textview for the username */
    private TextView userName;

    /** the recyclerView */
    private RecyclerView recyclerView;

    /** the list of UserFollowing objects */
    private List<UserFollowing> followingList = new ArrayList<>();

    /** the adapter */
    private RecyclerView.Adapter adapter;

    /** the input string */
    String input;

    /**
     * overridden onCreate method that gets all the TextViews and RecyclerViews.
     * This method all calls the method consumeFollowing that consumes the data.
     *
     * @param savedInstanceState the input savedInstanceState.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following);

        input = getIntent().getExtras().getString("user_for_following");

        userName = (TextView) findViewById(R.id.FollowingUser);
        userName.setText(input);

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerFollowing);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FollowingConnect(followingList, getApplicationContext(), R.layout.item_following);
        recyclerView.setAdapter(adapter);
        consumeFollowing();
    }

    /**
     * consumeFollowing method that sets all the data read from the json input.
     */
    public void consumeFollowing() {
        GitFollowing apiClient = GitClient.getClient().create(GitFollowing.class);
        Call<List<UserFollowing>> call = apiClient.getUserFollowing(input);
        call.enqueue(new Callback<List<UserFollowing>>() {
            @Override
            public void onResponse(Call<List<UserFollowing>> call, Response<List<UserFollowing>> response) {
                followingList.clear();
                followingList.addAll(response.body());
                adapter.notifyDataSetChanged();

                SharedPreferences preferences = getSharedPreferences("following_" + input, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                for (int i = 0; i < followingList.size(); i++) {
                    editor.putString("following" + i, followingList.get(i).getUserName());
                }
                editor.apply();
            }

            @Override
            public void onFailure(Call<List<UserFollowing>> call, Throwable t) {
                System.out.println("Unable to consume data" + t.toString());
            }
        });
    }
}
