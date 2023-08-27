package DataStore;

import DataObjects.Post;

public interface PostDataStore {
    void addPost(Post post);
    Post getPost(String postId);
    void updatePost(Post post);
}
