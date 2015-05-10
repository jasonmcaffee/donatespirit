package com.donatespirit.mvc.model;

/**
 * Created by jason on 5/10/15.
 */
public enum UserRole {
    COLEADER("coleader"), LEADER("leader"), ELDER("elder");

    private final String value;

    private UserRole(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
