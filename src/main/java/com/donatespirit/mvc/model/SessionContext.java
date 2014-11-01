package com.donatespirit.mvc.model;


public class SessionContext
{
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

}
