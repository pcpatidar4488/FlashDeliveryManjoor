package com.example.manjooralam.flashdelivery.models;

/**
 * Created by manjooralam on 9/27/2017.
 */

public class UserModel {
    public String imageUrl;
    public String uid;
    public String email;
    public String fullname;
    public String mobile;
    public UserModel(String imageUrl, String uid, String email, String fullname, String mobile) {
       this.imageUrl = imageUrl;
        this.uid = uid;
        this.email = email;
        this.fullname = fullname;
        this.mobile = mobile;
    }
}
