package com.androidexam.stayfinder.data.services;

import com.androidexam.stayfinder.data.apis.PostAPI;
import com.androidexam.stayfinder.data.models.Post;
import com.androidexam.stayfinder.data.models.request.PostRequest;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class PostRemoteService {
    PostAPI postAPI;

    @Inject
    public PostRemoteService(PostAPI postAPI) {
        this.postAPI = postAPI;
    }

    public Observable<List<Post>> getAllPost() {
        return postAPI.getAllPost();
    }

    public Observable<List<Post>> getPostByAccountName(String accountName) {
        return postAPI.getPostByAccountName(
                accountName);
    }

    public Observable<List<PostRequest>> getPostByAccountNameAndStatus(String accountName, int status) {
        return postAPI.getPostByAccountNameAndStatus(accountName, status);
    }

    public Observable<Boolean> changeStatusPost(int postId, int status) {
        return postAPI.changeStatusPost(postId, status);
    }
}
