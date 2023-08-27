package DataStore;

import DataObjects.User;

public interface UserDataStore {
    void signUpUser(User user);
    User getUser(String email);
    void updateUser(User user);
}
