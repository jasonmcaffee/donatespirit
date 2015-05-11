package com.donatespirit.mvc.model;



import javax.persistence.*;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by jason on 5/10/15.
 */
@Entity
public class UserRole implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private UserRoleType role;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserRoleType getRole() {
        return role;
    }

    public void setRole(UserRoleType role) {
        this.role = role;
    }

    private int userId;


}
