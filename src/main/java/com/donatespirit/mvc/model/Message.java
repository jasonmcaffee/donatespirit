package com.donatespirit.mvc.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {
    @Id @GeneratedValue private long id;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private long userId;

    @OneToOne
    @JoinColumn(name="id")
    private User user;

    private Date date;

    private String message;
}
