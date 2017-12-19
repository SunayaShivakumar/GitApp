package com.sunayashivakumar.github.model;

import com.google.gson.annotations.SerializedName;

/**
 * UserFollowing class that defines a following.
 *
 * @author Sunaya Shivakumar
 */
public class UserFollowing {
    /** the username of the following. */
    @SerializedName("login")
    private String userName;

    /** the avatar of the following. */
    @SerializedName("avatar_url")
    private String userAvatar;

    /**
     * UserFollowing constructor that set the class variables.
     *
     * @param userName the given username.
     */
    public UserFollowing(String userName,
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
