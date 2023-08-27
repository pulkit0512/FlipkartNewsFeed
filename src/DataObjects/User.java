package DataObjects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User {
    private String email;
    private String name;
    private boolean loggedIn;
    private List<Post> userPosts;
    private Map<String, User> followersMap;
    private LocalDateTime logInTime;
    private long durationMin;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public List<Post> getUserPosts() {
        if (userPosts == null) {
            userPosts = new ArrayList<>();
        }
        return userPosts;
    }

    public Map<String, User> getFollowersMap() {
        return followersMap;
    }

    public void setFollowersMap(Map<String, User> followersMap) {
        this.followersMap = followersMap;
    }

    public LocalDateTime getLogInTime() {
        return logInTime;
    }

    public void setLogInTime(LocalDateTime logInTime) {
        this.logInTime = logInTime;
    }

    public long getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(long durationMin) {
        this.durationMin = durationMin;
    }
}
