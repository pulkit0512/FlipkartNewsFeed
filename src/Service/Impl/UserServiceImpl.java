package Service.Impl;

import DataObjects.Comment;
import DataObjects.Post;
import DataObjects.User;
import DataObjects.VoteType;
import DataStore.Impl.PostDataStoreImpl;
import DataStore.Impl.UserDataStoreImpl;
import DataStore.PostDataStore;
import DataStore.UserDataStore;
import Service.UserService;
import Validations.UserActionValidations;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final UserDataStore userDataStore = new UserDataStoreImpl();
    private static final PostDataStore postDataStore = new PostDataStoreImpl();
    private static final UserActionValidations validator = new UserActionValidations();

    private static final String DOMAIN = "@Flipkart.com";

    @Override
    public void signUp(String name) {
        String email = name + DOMAIN;
        User user = userDataStore.getUser(email);

        if (!validator.checkUserNotInSystem(user)) {
            System.out.println("User already exist System. Kindly login.");
            return;
        }

        user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setFollowersMap(new HashMap<>());
        user.setLoggedIn(false);

        userDataStore.signUpUser(user);
        System.out.println("User: " + name + " signed up successfully.");
    }

    @Override
    public void logIn(String name) {
        String email = name + DOMAIN;
        User user = userDataStore.getUser(email);

        if (validator.checkUserNotInSystem(user)) {
            System.out.println("User not in System, kindly Sign Up.");
            return;
        }

        if (user.isLoggedIn()) {
            System.out.println("User already loggedIn.");
            return;
        }

        user.setLoggedIn(true);
        user.setLogInTime(LocalDateTime.now());
        userDataStore.updateUser(user);
        System.out.println("User: " + name + " Logged in Successfully.");
        System.out.println("User loggedIn at: " + user.getLogInTime());
    }

    @Override
    public void logOut(String name) {
        String email = name + DOMAIN;
        User user = userDataStore.getUser(email);

        boolean isUserNotAllowedToPost = validator.loggedInValidations(user);
        if (isUserNotAllowedToPost) {
            System.out.println("User not valid or is not loggedIn.");
            return;
        }

        user.setLoggedIn(false);
        long min = Duration.between(LocalDateTime.now(), user.getLogInTime()).getSeconds()/60;
        user.setDurationMin(min);
        userDataStore.updateUser(user);

        System.out.println("User: " + name + " Logged Out Successfully.");
        System.out.println("User was logged in for: " + min + " min of duration.");
    }

    @Override
    public void post(String name, String postMessage) {
        String email = name + DOMAIN;
        User user = userDataStore.getUser(email);

        boolean isUserNotAllowedToPost = validator.loggedInValidations(user);
        if (isUserNotAllowedToPost) {
            System.out.println("User not valid or is not loggedIn.");
            return;
        }

        Post post = new Post();
        post.setUserPosted(name);
        post.setMessage(postMessage);
        post.setTimeStamp(LocalDateTime.now());

        postDataStore.addPost(post);

        user.getUserPosts().add(post);
        userDataStore.updateUser(user);
        System.out.println("User: " + name + " posted to feed successfully.");
    }

    @Override
    public void follow(String userName, String userFollowed) {
        String userEmail = userName + DOMAIN;
        String userFollowedEmail = userFollowed + DOMAIN;

        User user = userDataStore.getUser(userEmail);
        boolean isUserNotAllowedToFollow = validator.loggedInValidations(user);
        if (isUserNotAllowedToFollow) {
            System.out.println("User not valid or is not loggedIn.");
            return;
        }

        User followUser = userDataStore.getUser(userFollowedEmail);
        if (validator.checkUserNotInSystem(followUser)) {
            System.out.println("User not in System.");
            return;
        }

        if (user.getFollowersMap().containsKey(userFollowedEmail)) {
            System.out.println("User: " + userName + " already follows " + userFollowed);
            return;
        }

        user.getFollowersMap().put(userFollowedEmail, followUser);
        userDataStore.updateUser(user);
        System.out.println("User: " + userName + " followed: " + userFollowed + " successfully.");
    }

    @Override
    public void reply(String userName, String postId, String replyMessage) {
        Post post = postDataStore.getPost(postId);

        if (validator.checkPostNotValid(post)) {
            System.out.println("Not a valid post.");
            return;
        }

        String userEmail = userName + DOMAIN;
        User user = userDataStore.getUser(userEmail);
        boolean isUserNotAllowedToReply = validator.loggedInValidations(user);
        if (isUserNotAllowedToReply) {
            System.out.println("User not valid or is not loggedIn.");
            return;
        }

        String postUserEmail = post.getUserPosted() + DOMAIN;
        if (!user.getFollowersMap().containsKey(postUserEmail)) {
            System.out.println("User: " + userName + " does not follow the user who has made this post.");
            return;
        }

        Comment comment = new Comment();
        comment.setCommentMessage(replyMessage);
        comment.setUserName(userName);
        comment.setCommentTimeStamp(LocalDateTime.now());
        post.getPostReplies().add(comment);
        postDataStore.updatePost(post);
        System.out.println("User comment added successfully.");
    }

    @Override
    public void vote(String userName, String postId, VoteType voteType) {
        Post post = postDataStore.getPost(postId);
        String userEmail = userName + DOMAIN;
        User user = userDataStore.getUser(userEmail);

        if (validator.checkPostNotValid(post)) {
            System.out.println("Not a valid post.");
            return;
        }

        boolean isUserNotAllowedToVote = validator.loggedInValidations(user);
        if (isUserNotAllowedToVote) {
            System.out.println("User not valid or is not loggedIn.");
            return;
        }

        String userPostedEmail = post.getUserPosted() + DOMAIN;
        if (!user.getFollowersMap().containsKey(userPostedEmail)) {
            System.out.println("User does not follow the user who has posted this post. So user can't vote on this");
            return;
        }

        if (voteType.equals(VoteType.DOWNVOTE)) {
            post.setDownVotes(post.getDownVotes() + 1);
        } else {
            post.setUpVotes(post.getUpVotes() + 1);
        }

        postDataStore.updatePost(post);
        System.out.println("Post: " + voteType + " successfully.");
    }

    @Override
    public void showNewsFeed(String userName) {
        String userEmail = userName + DOMAIN;
        User user = userDataStore.getUser(userEmail);

        boolean isUserNotAllowedToSeeFeed = validator.loggedInValidations(user);
        if (isUserNotAllowedToSeeFeed) {
            System.out.println("User not valid or is not loggedIn.");
            return;
        }

        List<Post> posts = new ArrayList<>(user.getUserPosts());

        for(String email : user.getFollowersMap().keySet()) {
            User followedUser = userDataStore.getUser(email);
            posts.addAll(followedUser.getUserPosts());
        }

        posts.sort(this::compare);

        for(Post post : posts) {
            System.out.println(post.getPostId());
            System.out.println(post.getUserPosted());
            System.out.println("UPVOTE: " + post.getUpVotes());
            System.out.println("DOWNVOTE: " + post.getDownVotes());
            System.out.println(post.getMessage());
            System.out.println(post.getTimeStamp());
            long min = Duration.between(LocalDateTime.now(), post.getTimeStamp()).getSeconds()/60;
            long hours = min/60;
            long days = hours/24;
            if (min<60) {
                System.out.println("Time: " + min + " min ago.");
            } else if (hours<24) {
                System.out.println("Time: " + hours + " hours ago.");
            } else {
                System.out.println("Time: " + days + " days ago.");
            }


            for(Comment comment : post.getPostReplies()) {
                System.out.println(comment.getUserName());
                System.out.println(comment.getCommentMessage());
                System.out.println(comment.getCommentTimeStamp());
            }

            System.out.println();
            System.out.println();
        }
    }

    private int compare(Post a, Post b) {
        int aScore = a.getUpVotes()-a.getDownVotes();
        int bScore = b.getUpVotes()-b.getDownVotes();

        if (aScore == bScore) {
            int aComments = a.getPostReplies().size();
            int bComments = b.getPostReplies().size();

            if (aComments == bComments) {
                LocalDateTime aTimeStamp = a.getTimeStamp();
                LocalDateTime bTimeStamp = b.getTimeStamp();

                if (aTimeStamp.isAfter(bTimeStamp)) {
                    return -1;
                } else if (aTimeStamp.isBefore(bTimeStamp)) {
                    return 1;
                } else {
                    return 0;
                }
            }

            return bComments - aComments;
        }

        return bScore - aScore;
    }
}
