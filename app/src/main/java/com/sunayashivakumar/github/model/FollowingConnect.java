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
 * FollowingConnect class that extends the RecyclerView Adapter
 *
 * @author Sunaya Shivakumar
 */
public class FollowingConnect extends RecyclerView.Adapter<FollowingConnect.FollowingHolder>{
    /** a list of UserFollowing objects */
    private List<UserFollowing> following;

    /** built-in context */
    private Context context;

    /** number of rows in the RecyclerView */
    private int row;

    /**
     * FollowingConnect constructor that takes in all necessary values and sets them
     * to the respective class variables.
     *
     * @param following list of given UserFollowing objects.
     * @param context the given context.
     * @param row the number of rows.
     */
    public FollowingConnect(List<UserFollowing> following,
                            Context context,
                            int row) {
        this.setFollowing(following);
        this.setContext(context);
        this.setRow(row);
    }

    /**
     * setter method for the following variable.
     *
     * @param following the input list of UserFollowing objects.
     */
    public void setFollowing(List<UserFollowing> following) {
        this.following = following;
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
     * getter method for the following variable.
     *
     * @return the following variable.
     */
    public List<UserFollowing> getFollowing() {
        return following;
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
     * @return FollowingHolder object.
     */
    @Override
    public FollowingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(row, parent, false);
        return new FollowingHolder(view);
    }

    /**
     * overridden onBindViewHolder method.
     *
     * @param holder FollowersHolder object.
     * @param position the position in the layout.
     */
    @Override
    public void onBindViewHolder(FollowingHolder holder, final int position) {
        Bitmap image = null;
        ProfileActivity.DownloadAvatar task = new ProfileActivity.DownloadAvatar();
        try {
            image = task.execute(following.get(position).getUserAvatar()).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.avatar.setImageBitmap(image);

        holder.name.setText(following.get(position).getUserName());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String followingUsername = following.get(position).getUserName();
                Intent profileActivity = new Intent(v.getContext(), ProfileActivity.class);
                profileActivity.putExtra("user_for_profile", followingUsername.toString());
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
        return following.size();
    }

    /**
     * static class FollowingHolder that gets the Following's names.
     */
    public static class FollowingHolder extends RecyclerView.ViewHolder {
        /** the LinearLayout within which we set the following items. */
        LinearLayout linearLayout;

        /** the avatar of the following set as an ImageView. */
        ImageView avatar;

        /** name of the following set as a button. */
        Button name;

        /**
         * FollowingHolder constructor, that gets the view objects.
         * @param view the given view.
         */
        public FollowingHolder(View view) {
            super(view);

            linearLayout = (LinearLayout) view.findViewById(R.id.item_following);
            avatar = (ImageView) view.findViewById(R.id.followingAvatar);
            name = (Button) view.findViewById(R.id.buttonFollowingName);
        }
    }
}
