package DataStore.Impl;

import DataObjects.Post;
import DataStore.PostDataStore;

import java.util.HashMap;
import java.util.Map;

public class PostDataStoreImpl implements PostDataStore {
    private static final Map<String, Post> postDB = new HashMap<>();

    @Override
    public void addPost(Post post) {
        postDB.put(post.getPostId(), post);
    }

    @Override
    public Post getPost(String postId) {
        return postDB.get(postId);
    }

    @Override
    public void updatePost(Post post) {
        postDB.put(post.getPostId(), post);
    }
}
