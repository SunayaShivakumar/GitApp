package com.sunayashivakumar.github.model;

import com.google.gson.annotations.SerializedName;

/**
 * UserFollower class that defines a follower.
 *
 * @author Sunaya Shivakumar
 */
public class UserFollower {
    /** the username of the follower. */
    @SerializedName("login")
    private String userName;

    /** the avatar of the follower. */
    @SerializedName("avatar_url")
    private String userAvatar;

    /**
     * UserFollower constructor that sets the class variables.
     *
     * @param userName the given username.
     * @param userAvatar the given user avatar url.
     */
    public UserFollower(String userName,
                        String userAvatar) {
        this.setUserName(userName);
        this.setUserAvatar(userAvatar);
    }

    /**
     * setter method for the userName variable.
     *
     * @param userName the input userName.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * setter method for the userAvatar variable.
     *
     * @param userAvatar the input user avatar url.
     */
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    /**
     * getter method for the userName variable.
     *
     * @return the userName variable.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * getter method for the userAvatar variable.
     *
     * @return the userAvatar variable.
     */
    public String getUserAvatar() {
        return userAvatar;
    }
}
