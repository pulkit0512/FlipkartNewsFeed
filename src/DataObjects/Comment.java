package DataObjects;

import java.time.LocalDateTime;

public class Comment {
    private static int uniqueId = 0;
    private final String commentId;
    private String commentMessage;
    private int noOfReplies;
    private String userName;
    private LocalDateTime commentTimeStamp;

    public Comment() {
        uniqueId++;
        this.commentId = "C"+uniqueId;
    }


    public String getCommentId() {
        return commentId;
    }

    public String getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage;
    }

    public int getNoOfReplies() {
        return noOfReplies;
    }

    public void setNoOfReplies(int noOfReplies) {
        this.noOfReplies = noOfReplies;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getCommentTimeStamp() {
        return commentTimeStamp;
    }

    public void setCommentTimeStamp(LocalDateTime commentTimeStamp) {
        this.commentTimeStamp = commentTimeStamp;
    }
}
