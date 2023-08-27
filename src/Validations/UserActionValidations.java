package Validations;

import DataObjects.Post;
import DataObjects.User;

public class UserActionValidations {

    public boolean checkUserNotInSystem(User user) {
        return user == null;
    }

    public boolean checkPostNotValid(Post post) {
        return post==null;
    }

    public boolean loggedInValidations (User user) {
        return checkUserNotInSystem(user) || !user.isLoggedIn();
    }
}
