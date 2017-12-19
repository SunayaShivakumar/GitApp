package com.sunayashivakumar.github.model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunayashivakumar.github.R;
import com.sunayashivakumar.github.view.ReposWebActivity;

import java.util.List;

/**
 * ReposConnect class that extends the RecyclerView Adapter
 *
 * @author Sunaya Shivakumar
 */
public class ReposConnect extends RecyclerView.Adapter<ReposConnect.ReposHolder> {
    /** a list of UserRepo objects */
    private List<UserRepo> repositories;

    /** built-in context */
    private Context context;

    /** number of rows in the RecyclerView */
    private int row;

    /**
     * ReposConnect constructor that takes in all necessary values and sets them
     * to the respective class variables.
     *
     * @param repositories list of given UserRepo objects.
     * @param context the given context.
     * @param row the number of rows.
     */
    public ReposConnect(List<UserRepo> repositories,
                        Context context,
                        int row) {
        this.setRepositories(repositories);
        this.setContext(context);
        this.setRow(row);
    }

    /**
     * setter method for the repositories variable.
     *
     * @param repositories the input list of UserRepo objects.
     */
    public void setRepositories(List<UserRepo> repositories) {
        this.repositories = repositories;
    }

    /**
     * setter method for the context.
     *
     * @param context the input context.
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * setter method for the number of rows.
     *
     * @param row the input number of rows.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * getter method for the repositories variable.
     *
     * @return the repositories variable.
     */
    public List<UserRepo> getRepositories() {
        return repositories;
    }

    /**
     * getter method for the context variable.
     *
     * @return the context variable.
     */
    public Context getContext() {
        return context;
    }

    /**
     * getter method for the row variable.
     *
     * @return the row variable.
     */
    public int getRow() {
        return row;
    }

    /**
     * overridden onCreateViewHolder method.
     *
     * @param parent ViewGroup parent.
     * @param viewType int viewType.
     * @return ReposHolder object.
     */
    @Override
    public ReposHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(row, parent, false);
        return new ReposHolder(view);
    }

    /**
     * overridden onBindViewHolder method.
     *
     * @param holder ReposHolder object.
     * @param position the position in the layout.
     */
    @Override
    public void onBindViewHolder(ReposHolder holder, final int position) {
        holder.name.setText(repositories.get(position).getRepoName());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String webLink = repositories.get(position).getWebUrl();
                Intent webRepoActivity = new Intent(v.getContext(), ReposWebActivity.class);
                webRepoActivity.putExtra("web_link", webLink);
                context.startActivity(webRepoActivity);
            }
        });

        holder.description.setText(repositories.get(position).getDescription());
        holder.language.setText("Language: " + repositories.get(position).getLanguage());
        holder.stars.setText("Stars: " + repositories.get(position).getStars());
    }

    /**
     * overridden getItemCount method.
     *
     * @return the number of items in the layout.
     */
    @Override
    public int getItemCount() {
        return repositories.size();
    }

    /**
     * static class ReposHolder that gets the Repositories's names, descriptions, languages, and stars.
     */
    public static class ReposHolder extends RecyclerView.ViewHolder {
        /** the LinearLayout within which we set the following items. */
        LinearLayout linearLayout;

        /** name of the repository set as a button. */
        Button name;

        /** description of the repository */
        TextView description;

        /** language of the repository */
        TextView language;

        /** the number of stars for the repository */
        TextView stars;

        /**
         * ReposHolder constructor, that gets the view objects.
         * @param view the given view.
         */
        public ReposHolder(View view) {
            super(view);

            linearLayout = (LinearLayout) view.findViewById(R.id.item_repos);
            name = (Button) view.findViewById(R.id.buttonRepoName);
            description = (TextView) view.findViewById(R.id.repoDescription);
            language = (TextView) view.findViewById(R.id.repoLanguage);
            stars = (TextView) view.findViewById(R.id.repoStars);
        }
    }
}
