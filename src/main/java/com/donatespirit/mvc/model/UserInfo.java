package com.donatespirit.mvc.model;

//http://gerrydevstory.com/2013/06/29/spring-mvc-hibernate-mysql-quick-start-from-scratch/


import java.io.Serializable;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//
//
@Entity
public class UserInfo implements Serializable {
    @Id @GeneratedValue private long id;
    private long userId;
    private String email;
    private String creationIp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreationIp() {
        return creationIp;
    }

    public void setCreationIp(String creationIp) {
        this.creationIp = creationIp;
    }
}