package com.sunayashivakumar.github.model;

import com.google.gson.annotations.SerializedName;

/**
 * UserProfile class that defines what we need in a user profile.
 *
 * @author Sunaya Shivakumar
 */
public class UserProfile {
    /** the avatar image url. */
    @SerializedName("avatar_url")
    private String avatar;

    /** the full name of the user. */
    @SerializedName("name")
    private String fullName;

    /** the username or login of the user. */
    @SerializedName("login")
    private String userName;

    /** the number of public repos of the user. */
    @SerializedName("public_repos")
    private String repos;

    /** the number of followers of the user. */
    @SerializedName("followers")
    private String followers;

    /** the number of people the user is following. */
    @SerializedName("following")
    private String following;

    /** the user's bio. */
    @SerializedName("bio")
    private String bio;

    /** the user's blog link. */
    @SerializedName("blog")
    private String link;

    /** the user's email. */
    @SerializedName("email")
    private String email;

    /** the date at which the user's account was created at. */
    @SerializedName("created_at")
    private String createdAt;

    /**
     * UserProfile constructor that sets all the class variables.
     *
     * @param avatar the input avatar.
     * @param fullName the input name.
     * @param userName the input username.
     * @param repos the input number of repos.
     * @param followers the input number of followers.
     * @param following the input number of following.
     * @param bio the input bio.
     * @param link the input link.
     * @param email the input email.
     * @param createdAt the input date createdAt.
     */
    public UserProfile(String avatar,
                       String fullName,
                       String userName,
                       String repos,
                       String followers,
                       String following,
                       String bio,
                       String link,
                       String email,
                       String createdAt) {
        this.setAvatar(avatar);
        this.setFullName(fullName);
        this.setUserName(userName);
        this.setRepos(repos);
        this.setFollowers(followers);
        this.setFollowing(following);
        this.setBio(bio);
        this.setLink(link);
        this.setEmail(email);
        this.setCreatedAt(createdAt);
    }

    /**
     * setter method for the avatar variable
     *
     * @param avatar the input variable
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * setter method for the fullName variable
     *
     * @param fullName the input variable
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * setter method for the userName variable
     *
     * @param userName the input variable
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * setter method for the repos variable
     *
     * @param repos the input variable
     */
    public void setRepos(String repos) {
        this.repos = repos;
    }

    /**
     * setter method for the followers variable
     *
     * @param followers the input variable
     */
    public void setFollowers(String followers) {
        this.followers = followers;
    }

    /**
     * setter method for the following variable
     *
     * @param following the input variable
     */
    public void setFollowing(String following) {
        this.following = following;
    }

    /**
     * setter method for the bio variable
     *
     * @param bio the input variable
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * setter method for the link variable
     *
     * @param link the input variable
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * setter method for the email variable
     *
     * @param email the input variable
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * setter method for the createdAt variable
     *
     * @param createdAt the input variable
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * getter method for the avatar variable
     *
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * getter method for the fullname variable
     *
     * @return the fullname
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * getter method for the userName variable
     *
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * getter method for the repos variable
     *
     * @return the repos
     */
    public String getRepos() {
        return repos;
    }

    /**
     * getter method for the followers variable
     *
     * @return the followers
     */
    public String getFollowers() {
        return followers;
    }

    /**
     * getter method for the following variable
     *
     * @return the following
     */
    public String getFollowing() {
        return following;
    }

    /**
     * getter method for the bio variable
     *
     * @return the bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * getter method for the link variable
     *
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * getter method for the email variable
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * getter method for the createdAt variable
     *
     * @return the createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }
}
