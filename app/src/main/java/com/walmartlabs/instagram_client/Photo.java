package com.walmartlabs.instagram_client;

import java.io.Serializable;

/**
 * Created by akulka2 on 10/9/15.
 */
public class Photo implements Serializable{

    private static final long serialVersionUID = -8092928984839263322L;
    private String url;
    private String caption;
    private String userName;
    private String profilePictureUrl;
    private int likes;
    private String time;
    private int commentsCount;
    private String lastCommenterName;
    private String lastComment;
    private String secondLastCommenterName;
    private String secondLastComment;
    private String id;

    public String getLastCommenterName() {
        return lastCommenterName;
    }

    public void setLastCommenterName(String lastCommenterName) {
        this.lastCommenterName = lastCommenterName;
    }

    public String getSecondLastCommenterName() {
        return secondLastCommenterName;
    }

    public void setSecondLastCommenterName(String secondLastCommenterName) {
        this.secondLastCommenterName = secondLastCommenterName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getLastComment() {
        return lastComment;
    }

    public void setLastComment(String lastComment) {
        this.lastComment = lastComment;
    }

    public String getSecondLastComment() {
        return secondLastComment;
    }

    public void setSecondLastComment(String secondLastComment) {
        this.secondLastComment = secondLastComment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
