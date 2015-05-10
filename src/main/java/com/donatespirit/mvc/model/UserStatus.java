package com.donatespirit.mvc.model;

/**
 * Created by jason on 5/10/15.
 */
public enum UserStatus {
    UNNAPPROVED(0), APPROVED(1), REJECTED(-1);

    private final int value;

    private UserStatus(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
