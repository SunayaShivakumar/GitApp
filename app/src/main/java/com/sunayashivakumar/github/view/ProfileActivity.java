package com.sunayashivakumar.github.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunayashivakumar.github.R;
import com.sunayashivakumar.github.controller.GitProfile;
import com.sunayashivakumar.github.controller.GitClient;
import com.sunayashivakumar.github.model.UserProfile;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * ProfileActivity class for the user profile view.
 *
 * @author Sunaya Shivakumar
 */
public class ProfileActivity extends AppCompatActivity {
    /** the user avatar image */
    private ImageView userAvatar;

    /** the user's full name */
    private TextView userFullname;

    /** the user's login name. */
    private TextView userName;

    /** the user's repos count button */
    private Button userRepos;

    /** the user's followers count button */
    private Button userFollowers;

    /** the user's following count button */
    private Button userFollowing;

    /** the user's bio text view */
    private TextView userBio;

    /** the user's blog link text */
    private TextView userLink;

    /** the user's email text */
    private TextView userEmail;

    /** the user's createdAt date */
    private TextView userActive;

    /** the reposActivity to transition to */
    private Intent reposActivity;

    /** the followersActivity to transition to */
    private Intent followersActivity;

    /** the followingActivity to transition to */
    private Intent followingActivity;

    /** the input string */
    String input;

    /** the input image */
    Bitmap image;

    /**
     * the overridden onCreate method that gets all the TextViews, ImageViews, and buttons.
     * It also calls the consumeData method that sets all the views.
     *
     * @param savedInstanceState the input savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userAvatar = (ImageView) findViewById(R.id.userAvatar);
        userFullname = (TextView) findViewById(R.id.userFullname);
        userName = (TextView) findViewById(R.id.userName);
        userRepos = (Button) findViewById(R.id.buttonRepos);
        userFollowers = (Button) findViewById(R.id.buttonFollowers);
        userFollowing = (Button) findViewById(R.id.buttonFollowing);
        userBio = (TextView) findViewById(R.id.userBio);
        userLink = (TextView) findViewById(R.id.userLink);
        userEmail = (TextView) findViewById(R.id.userEmail);
        userActive = (TextView) findViewById(R.id.userActive);

        input = getIntent().getExtras().getString("user_for_profile");

        consumeData();
    }

    /**
     * getUserRepos method that sets up an intent to transition to the reposActivity view.
     *
     * @param view the input view.
     */
    public void getUserRepos(View view) {
        reposActivity = new Intent(ProfileActivity.this, ReposActivity.class);
        reposActivity.putExtra("user_for_repos", input);
        startActivity(reposActivity);
    }

    /**
     * getUserFollowers method that sets up an intent to transition to the followersActivity view.
     *
     * @param view the input view.
     */
    public void getUserFollowers(View view) {
        followersActivity = new Intent(ProfileActivity.this, FollowersActivity.class);
        followersActivity.putExtra("user_for_followers", input);
        startActivity(followersActivity);
    }

    /**
     * getUserFollowing method that sets up an intent to transition to the followingActivity view.
     *
     * @param view the input view.
     */
    public void getUserFollowing(View view) {
        followingActivity = new Intent(ProfileActivity.this, FollowingActivity.class);
        followingActivity.putExtra("user_for_following", input);
        startActivity(followingActivity);
    }

    /**
     * consumeData method that sets all the texts and images to the TextViews, ImageViews, and Buttons
     * in the profileActivity view. Also calls the DownloadAvatar method.
     *
     * shared preferences: https://stackoverflow.com/questions/12432585/android-shared-preferences-with-multiple-activities
     */
    public void consumeData() {
        GitProfile apiClient = GitClient.getClient().create(GitProfile.class);
        Call<UserProfile> call;

        call = apiClient.getUserProfile(input);

        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                SharedPreferences preferences = getSharedPreferences("profile_" + input, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                // set the avatar
                editor.putString("avatar_url", response.body().getAvatar());

                DownloadAvatar task = new DownloadAvatar();
                try {
                    image = task.execute(response.body().getAvatar()).get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                userAvatar.setImageBitmap(image);

                // set the name
                if (response.body().getFullName() == null) {
                    userFullname.setText("User Name");
                } else {
                    userFullname.setText(response.body().getFullName());
                }
                editor.putString("name", userFullname.getText().toString());

                // set the login
                if (response.body().getUserName() == null) {
                    userName.setText("User Login");
                } else {

                }
                editor.putString("login", userName.getText().toString());

                // set the repos
                editor.putString("public_repos", response.body().getRepos());
                userRepos.setText("Repositories: " + response.body().getRepos());


                // set the followers
                editor.putString("followers", response.body().getFollowers());
                userFollowers.setText("Followers: " + response.body().getFollowers());

                // set the following
                editor.putString("following", response.body().getFollowing());
                userFollowing.setText("Following: " + response.body().getFollowing());

                // set the bio
                if (response.body().getBio() == null) {
                    userBio.setText("User bio is empty");
                } else {
                    userBio.setText(response.body().getBio());
                }
                editor.putString("bio", userBio.getText().toString());

                // set the blog
                if (response.body().getLink().isEmpty()) {
                    userLink.setText("User blog link is empty");
                } else {
                    userLink.setText(response.body().getLink());
                }
                editor.putString("blog", userLink.getText().toString());

                // set the email
                if (response.body().getEmail() == null) {
                    userEmail.setText("User email is empty");
                } else {
                    userEmail.setText(response.body().getEmail());
                }
                editor.putString("email", userEmail.getText().toString());

                // set the date
                String dateRaw = response.body().getCreatedAt();
                String date = dateRaw.substring(0, 10);
                userActive.setText("Joined Github on: " + date);
                editor.putString("created_at", response.body().getCreatedAt());

                editor.apply();
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                System.out.println("Unable to consume data" + t.toString());
            }
        });
    }

    /**
     * asyncTask class called DownloadAvatar that downloads an image to display.
     */
    public static class DownloadAvatar extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap avatar = BitmapFactory.decodeStream(inputStream);
                return avatar;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
