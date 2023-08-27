package Service;

import DataObjects.VoteType;

public interface UserService {
    void signUp(String name);
    void logIn(String name);
    void logOut(String name);
    void post(String name, String postMessage);
    void follow(String userName, String userFollowed);
    void reply(String userName, String postId, String replyMessage);
    void vote(String userName, String postId, VoteType voteType);
    void showNewsFeed(String userName); // Will show user own post + post of users followed.
}
