package DataObjects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {
    private static int uniqueId = 0;
    private final String postId;
    private int upVotes;
    private int downVotes;
    private String message;
    private LocalDateTime timeStamp;
    private String userPosted;
    private List<Comment> postReplies;

    public Post() {
        uniqueId++;
        this.postId = "P"+uniqueId;
    }

    public String getPostId() {
        return postId;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUserPosted() {
        return userPosted;
    }

    public void setUserPosted(String userPosted) {
        this.userPosted = userPosted;
    }

    public List<Comment> getPostReplies() {
        if (postReplies == null) {
            postReplies = new ArrayList<>();
        }
        return postReplies;
    }
}
