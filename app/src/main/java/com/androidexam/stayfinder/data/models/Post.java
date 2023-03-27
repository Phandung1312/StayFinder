package com.androidexam.stayfinder.data.models;

import java.sql.Timestamp;
import java.util.List;

public class Post {
    private int id;
    private Account account;
    private String title;
    private String content;
    private int numOfFav;
    private boolean status;
    private Timestamp postTime;
    private List<Image> images;
    private Hostel hostel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumOfFav() {
        return numOfFav;
    }

    public void setNumOfFav(int numOfFav) {
        this.numOfFav = numOfFav;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }
}