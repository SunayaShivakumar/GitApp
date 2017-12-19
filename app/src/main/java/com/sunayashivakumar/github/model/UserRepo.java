package com.sunayashivakumar.github.model;

import com.google.gson.annotations.SerializedName;

/**
 * UserRepo class that defines what we need in a user repo object.
 *
 * @author Sunaya Shivakumar
 */
public class UserRepo {
    /** the name of the repo. */
    @SerializedName("name")
    private String repoName;

    /** the description of the repo. */
    @SerializedName("description")
    private String description;

    /** the language of the repo. */
    @SerializedName("language")
    private String language;

    /** the stars count of the repo. */
    @SerializedName("stargazers_count")
    private String stars;

    /** the url to access the repo. */
    @SerializedName("html_url")
    private String webUrl;

    /**
     * UserRepo constructor that sets all the class variables.
     *
     * @param name the input name.
     * @param description the input description.
     * @param language the input language.
     * @param stars the input stars.
     * @param webUrl the input web url.
     */
    public UserRepo(String name,
                    String description,
                    String language,
                    String stars,
                    String webUrl) {
        this.setRepoName(name);
        this.setDescription(description);
        this.setLanguage(language);
        this.setStars(stars);
        this.setWebUrl(webUrl);
    }

    /**
     * setter method for the repoName variable.
     *
     * @param repoName the input variable.
     */
    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    /**
     * setter method for the description variable.
     *
     * @param description the input variable.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * setter method for the language variable.
     *
     * @param language the input variable.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * setter method for the stars variable.
     *
     * @param stars the input variable.
     */
    public void setStars(String stars) {
        this.stars = stars;
    }

    /**
     * setter method for the webUrl variable.
     *
     * @param webUrl the input variable.
     */
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    /**
     * getter method for the repoName variable.
     *
     * @return the repoName.
     */
    public String getRepoName() {
        return repoName;
    }

    /**
     * getter method for the description variable.
     *
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * getter method for the language variable.
     *
     * @return the language.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * getter method for the stars variable.
     *
     * @return the stars.
     */
    public String getStars() {
        return stars;
    }

    /**
     * getter method for the webUrl variable.
     *
     * @return the webUrl.
     */
    public String getWebUrl() {
        return webUrl;
    }
}
