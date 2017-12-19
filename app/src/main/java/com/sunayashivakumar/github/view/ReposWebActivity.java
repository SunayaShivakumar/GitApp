package com.sunayashivakumar.github.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.sunayashivakumar.github.R;

/**
 * ReposWebActivity class for the user repos web view.
 *
 * @author Sunaya Shivakumar
 */
public class ReposWebActivity extends AppCompatActivity {
    /** the webView to display the repo in */
    private WebView webView;

    /** the input repo web link */
    String repoWebLink;

    /**
     * overridden onCreate method that gets and sets the webView.
     *
     * @param savedInstanceState the input savedInstanceState.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos_web);

        repoWebLink = getIntent().getExtras().getString("web_link");

        // webView activity: https://www.youtube.com/watch?v=2fRVvGkRJE8
        webView = (WebView) findViewById(R.id.webRepo);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(repoWebLink);
    }
}
