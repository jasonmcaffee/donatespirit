package com.donatespirit.mvc.model;

import org.springframework.web.util.HtmlUtils;

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

    public String getFormattedMessage(){
        //html encode everything but the <br/>
        String message = HtmlUtils.htmlEscape(getMessage());
        String formatted = message.replaceAll("\n", "<br>");
        return formatted;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private long userId;

    @OneToOne
    @JoinColumn(name="userId", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    private Date date;

    private String message;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    private String ip;
}
