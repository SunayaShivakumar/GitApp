package com.sunayashivakumar.github.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sunayashivakumar.github.R;
import com.sunayashivakumar.github.view.ProfileActivity;

import java.util.List;

/**
 * FollowersConnect class that extends the RecyclerView Adapter
 *
 * @author Sunaya Shivakumar
 */
public class FollowersConnect extends RecyclerView.Adapter<FollowersConnect.FollowersHolder>{
    /** a list of UserFollower objects */
    private List<UserFollower> followers;

    /** built-in context */
    private Context context;

    /** number of rows in the RecyclerView */
    private int row;

    /**
     * FollowersConnect constructor that takes in all necessary values and sets them
     * to the respective class variables.
     *
     * @param followers list of given UserFollower objects.
     * @param context the given context.
     * @param row the number of rows.
     */
    public FollowersConnect(List<UserFollower> followers,
                            Context context,
                            int row) {
        this.setFollowers(followers);
        this.setContext(context);
        this.setRow(row);
    }

    /**
     * setter method for the followers variable.
     *
     * @param followers the input list of UserFollower objects.
     */
    public void setFollowers(List<UserFollower> followers) {
        this.followers = followers;
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
     * getter method for the followers variable.
     *
     * @return the followers variable.
     */
    public List<UserFollower> getFollowers() {
        return followers;
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
     * @return FollowersHolder object.
     */
    @Override
    public FollowersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(row, parent, false);
        return new FollowersHolder(view);
    }

    /**
     * overridden onBindViewHolder method.
     *
     * @param holder FollowersHolder object.
     * @param position the position in the layout.
     */
    @Override
    public void onBindViewHolder(FollowersHolder holder, final int position) {
        Bitmap image = null;
        ProfileActivity.DownloadAvatar task = new ProfileActivity.DownloadAvatar();
        try {
            image = task.execute(followers.get(position).getUserAvatar()).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.avatar.setImageBitmap(image);

        holder.name.setText(followers.get(position).getUserName());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String followerUsername = followers.get(position).getUserName();
                Intent profileActivity = new Intent(v.getContext(), ProfileActivity.class);
                profileActivity.putExtra("user_for_profile", followerUsername.toString());
                context.startActivity(profileActivity);
            }
        });
    }

    /**
     * overridden getItemCount method.
     *
     * @return the number of items in the layout.
     */
    @Override
    public int getItemCount() {
        return followers.size();
    }

    /**
     * static class FollowersHolder that gets the Followers' names.
     */
    public static class FollowersHolder extends RecyclerView.ViewHolder {
        /** the LinearLayout within which we set the followers items. */
        LinearLayout linearLayout;

        /** the avatar of the follower set as an ImageView. */
        ImageView avatar;

        /** name of the follower set as a button. */
        Button name;

        /**
         * FollowersHolder constructor, that gets the view objects.
         * @param view the given view.
         */
        public FollowersHolder(View view) {
            super(view);

            linearLayout = (LinearLayout) view.findViewById(R.id.item_followers);
            avatar = (ImageView) view.findViewById(R.id.followerAvatar);
            name = (Button) view.findViewById(R.id.buttonFollowerName);
        }
    }
}
