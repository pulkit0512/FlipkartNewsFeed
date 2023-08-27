package DataStore.Impl;

import DataObjects.User;
import DataStore.UserDataStore;

import java.util.HashMap;
import java.util.Map;

public class UserDataStoreImpl implements UserDataStore {
    private static final Map<String, User> userDB = new HashMap<>();

    @Override
    public void signUpUser(User user) {
        userDB.put(user.getEmail(), user);
    }

    @Override
    public User getUser(String email) {
        return userDB.get(email);
    }

    @Override
    public void updateUser(User user) {
        userDB.put(user.getEmail(), user);
    }
}
