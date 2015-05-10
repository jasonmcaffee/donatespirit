package com.donatespirit.mvc.model;

import javax.persistence.Entity;

/**
 * Created by jason on 5/10/15.
 */

public enum UserRoleType {
    COLEADER("coleader"), LEADER("leader"), ELDER("elder");

    private final String value;

    private UserRoleType(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
