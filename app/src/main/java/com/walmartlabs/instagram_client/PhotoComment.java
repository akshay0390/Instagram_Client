package com.walmartlabs.instagram_client;

/**
 * Created by akulka2 on 10/11/15.
 */
public class PhotoComment {

    private String commenterUserName;
    private String commentText;

    public String getCommenterUserName() {
        return commenterUserName;
    }

    public void setCommenterUserName(String commenterUserName) {
        this.commenterUserName = commenterUserName;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
