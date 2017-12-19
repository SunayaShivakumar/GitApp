package com.sunayashivakumar.github.view;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sunayashivakumar.github.R;
import com.sunayashivakumar.github.controller.GitClient;
import com.sunayashivakumar.github.controller.GitRepo;
import com.sunayashivakumar.github.model.ReposConnect;
import com.sunayashivakumar.github.model.UserRepo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * ReposActivity class for the user repos view.
 *
 * @author Sunaya Shivakumar
 */
public class ReposActivity extends AppCompatActivity {
    /** the username of the user for which we are showing the repos. */
    private TextView userName;

    /** the recycler view */
    private RecyclerView recyclerView;

    /** the list of UserRepo objects */
    private List<UserRepo> reposList = new ArrayList<>();

    /** the adapter */
    private RecyclerView.Adapter adapter;

    /** the input string */
    String input;

    /**
     * overridden onCreate method that gets all the TextViews and RecyclerViews.
     * This method all calls the method consumeRepos that consumes the data.
     *
     * @param savedInstanceState the input savedInstanceState.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        input = getIntent().getExtras().getString("user_for_repos");

        userName = (TextView) findViewById(R.id.ReposUser);
        userName.setText(input);

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerRepos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReposConnect(reposList, getApplicationContext(), R.layout.item_repos);
        recyclerView.setAdapter(adapter);
        consumeRepos();
    }

    /**
     * consumeRepos method that sets all the data read from the json input.
     */
    public void consumeRepos() {
        GitRepo apiClient = GitClient.getClient().create(GitRepo.class);
        Call<List<UserRepo>> call = apiClient.getUserRepos(input);
        call.enqueue(new Callback<List<UserRepo>>() {
            @Override
            public void onResponse(Call<List<UserRepo>> call, Response<List<UserRepo>> response) {
                reposList.clear();
                reposList.addAll(response.body());
                adapter.notifyDataSetChanged();

                SharedPreferences preferences = getSharedPreferences("repos_" + input, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                for (int i = 0; i < reposList.size(); i++) {
                    editor.putString("repo" + i, reposList.get(i).getRepoName());
                }
                editor.apply();
            }

            @Override
            public void onFailure(Call<List<UserRepo>> call, Throwable t) {
                System.out.println("Unable to consume data" + t.toString());
            }
        });
    }
}
