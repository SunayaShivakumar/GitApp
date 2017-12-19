package com.sunayashivakumar.github.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sunayashivakumar.github.R;
import com.sunayashivakumar.github.controller.GitClient;
import com.sunayashivakumar.github.controller.GitLogin;
import com.sunayashivakumar.github.model.AccessToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * LoginActivity class for the login view.
 * OAuth login: https://www.youtube.com/watch?time_continue=312&v=TnQUb-ACqWs
 *
 * @author Sunaya Shivakumar
 */
public class LoginActivity extends AppCompatActivity {
    /** the client id for the app. */
    private String clientId = "3c298829abfa5a9f5702";

    /** the client secret for the app. */
    private String clientSecret = "27ef6a165b28abcd830384d141bb0788211fc699";

    /** the redirect url to use for returning to the app. */
    private String redirectUri = "https://gitcallback";

    /** the access token returned after sign in. */
    public static String access_token;

    /** the search button */
    private Button buttonSearch;

    /** the login button */
    private Button buttonLogin;

    /** the editable user login name. */
    private EditText userLoginName;

    /** the profileActivity to transition to. */
    private Intent profileActivity;

    /** the loginActivity to transition to. */
    private Intent loginActivity;

    /**
     * overridden onCreate method that gets all the TextViews and Buttons.
     *
     * @param savedInstanceState the input savedInstanceState.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userLoginName = (EditText) findViewById(R.id.userLoginName);
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
    }

    /**
     * getUserProfile method that sets up an intent to transition to the
     * profile activity view in the application.
     *
     * @param view the input view.
     */
    public void getUserProfile(View view) {
        profileActivity = new Intent(LoginActivity.this, ProfileActivity.class);
        profileActivity.putExtra("user_for_profile", userLoginName.getText().toString());
        startActivity(profileActivity);
    }

    /**
     * getUserLoggedIn signs in the user by opening the GitHub webflow for sign in.
     *
     * @param view the input view.
     */
    public void getUserLoggedIn(View view) {
        loginActivity = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://github.com/login/oauth/authorize"
                        + "?client_id=" + clientId
                        + "&scope=user%20repo&redirect_uri=" + redirectUri));
        startActivity(loginActivity);
    }

    /**
     * overridden onResume method that returns the user back to the app after
     * signing in through the GitHub sign in webflow.
     * It also prints a Toast saying if the sign in was successful or not.
     */
    @Override
    protected void onResume() {
        super.onResume();

        Uri uri = getIntent().getData();

        if (uri != null && uri.toString().startsWith(redirectUri)) {
            String authCode = uri.getQueryParameter("code");

            GitLogin apiClient = GitClient.getClientLoggedIn().create(GitLogin.class);
            Call<AccessToken> call = apiClient.getToken(clientId, clientSecret, authCode);
            call.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    access_token = response.body().getAccessToken();
                    Toast.makeText(LoginActivity.this, "Successful login", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    System.out.println("Unable to log in" + t.toString());
                }
            });
        }
    }
}
