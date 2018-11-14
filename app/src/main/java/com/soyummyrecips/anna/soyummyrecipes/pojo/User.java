package com.soyummyrecips.anna.soyummyrecipes.pojo;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import java.util.List;

/**
 */

public class User extends SugarRecord {


    //@SerializedName("name")
    public String username;
    //@SerializedName("job")
    public String emailid;
    //@SerializedName("id")
    public int phonenumber;
    //@SerializedName("createdAt")
    public String password;


    public User(String username,String emailid) {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static List<User> getListofLoginUsers(){
        List<User> users = User.listAll(User.class);
            return users;
    }

    public static void saveUser(User lUser){
        User mUser = lUser;
        mUser.save();
    }
}
