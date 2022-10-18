package com.skilltracker.app.addprofile.service.responses;

import com.skilltracker.app.addprofile.service.entity.User;

import java.util.List;

public class UserSearchResponse {
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
