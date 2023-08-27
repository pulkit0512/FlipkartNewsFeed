import DataObjects.VoteType;
import Service.Impl.UserServiceImpl;
import Service.UserService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static final File FILE = new File("/Users/pulkitarora/learning/MachineCoding/input.txt");
    private static final UserService userService = new UserServiceImpl();

    private static final Scanner sc;

    static {
        try {
            sc = new Scanner(FILE);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        System.out.println("Welcome to Machine Coding Round.");

        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            System.out.println("===" + input + "===");


            String[] params = input.split(" ");

            if (params[0].equalsIgnoreCase("signUp")) {
                String name = params[1].toUpperCase();
                userService.signUp(name);
            } else if (params[0].equalsIgnoreCase("login")) {
                String name = params[1].toUpperCase();
                userService.logIn(name);
            } else if (params[0].equalsIgnoreCase("post")) {
                String name = params[1].toUpperCase();
                String message = params[2];
                userService.post(name, message);
            } else if (params[0].equalsIgnoreCase("follow")) {
                String name = params[1].toUpperCase();
                String userFollowed = params[2].toUpperCase();
                userService.follow(name, userFollowed);
            } else if (params[0].equalsIgnoreCase("reply")) {
                String name = params[1].toUpperCase();
                String postId = params[2].toUpperCase();
                String replyMessage = params[3];
                userService.reply(name, postId, replyMessage);
            } else if (params[0].equalsIgnoreCase("upvote")) {
                String name = params[1].toUpperCase();
                String postId = params[2].toUpperCase();
                userService.vote(name, postId, VoteType.UPVOTE);
            } else if (params[0].equalsIgnoreCase("downVote")) {
                String name = params[1].toUpperCase();
                String postId = params[2].toUpperCase();
                userService.vote(name, postId, VoteType.DOWNVOTE);
            } else if (params[0].equalsIgnoreCase("showNewsFeed")) {
                String name = params[1].toUpperCase();
                userService.showNewsFeed(name);
            } else if (params[0].equalsIgnoreCase("logout")) {
                String name = params[1].toUpperCase();
                userService.logOut(name);
            }
        }
    }
}