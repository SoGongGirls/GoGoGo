package com.example.gogogo.community;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class PostItem {
    private String documentId;
    private String title;
    private String contents;
    private String nickname;
    private Integer like;
    private String postId;
    @ServerTimestamp
    private Date date;

    public PostItem(String documentId, String nickname, String title, String contents, String postId) {
        this.documentId = documentId;
        this.title = title;
        this.contents = contents;
        this.nickname = nickname;
        this.postId = postId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "PostItem{" +
                "documentId='" + documentId + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", nickname='" + nickname + '\'' +
                ", date=" + date +
                '}';
    }
}
