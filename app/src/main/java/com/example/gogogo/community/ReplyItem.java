package com.example.gogogo.community;

import java.util.Date;

public class ReplyItem {

    private String documentId;
    private String contents;
    private String nickname;
    private String replyId;
    private String date;

    public ReplyItem(String documentId, String contents, String nickname, String replyId, String date) {
        this.documentId = documentId;
        this.contents = contents;
        this.nickname = nickname;
        this.replyId = replyId;
        this.date = date;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
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

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ReplyItem{" +
                "documentId='" + documentId + '\'' +
                ", contents='" + contents + '\'' +
                ", nickname='" + nickname + '\'' +
                ", replyId='" + replyId + '\'' +
                ", date=" + date +
                '}';
    }
}
