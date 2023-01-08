package com.sogonggirls.gogogo.community;

public class PostItem {
    private String documentId;
    private String title;
    private String contents;
    private String nickname;
    private Integer like;
    private String postId;
    private String  date;

    public PostItem(String documentId, String nickname, String title, String contents, String postId) {
        this.documentId = documentId;
        this.title = title;
        this.contents = contents;
        this.nickname = nickname;
        this.postId = postId;
    }
    public PostItem(String documentId, String nickname, String title, String contents, String postId, String date){
        this.documentId = documentId;
        this.title = title;
        this.contents = contents;
        this.nickname = nickname;
        this.postId = postId;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
