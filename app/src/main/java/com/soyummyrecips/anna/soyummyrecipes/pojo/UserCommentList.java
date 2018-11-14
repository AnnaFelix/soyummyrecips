package com.soyummyrecips.anna.soyummyrecipes.pojo;

import com.orm.SugarRecord;

public class UserCommentList extends SugarRecord {

    //@SerializedName("name")
    public User user;
    //@SerializedName("job")
    public String respiesID;

    public String usercomment;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRespiesID() {
        return respiesID;
    }

    public void setRespiesID(String respiesID) {
        this.respiesID = respiesID;
    }

    public String getUsercomment() {
        return usercomment;
    }

    public void setUsercomment(String usercomment) {
        this.usercomment = usercomment;
    }
}
