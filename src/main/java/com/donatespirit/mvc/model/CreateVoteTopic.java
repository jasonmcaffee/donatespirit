package com.donatespirit.mvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by jason on 2/22/15.
 */
@Entity
@Table(name = "VoteTopic")
public class CreateVoteTopic {
    @Id
    @GeneratedValue
    private long id;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    private Date date;
    public Date getDate() {  return date;  }
    public void setDate(Date date) {  this.date = date; }

    private String topic;
    public String getTopic(){return topic;}
    public void setTopic(String topic){this.topic = topic;}

    /**
     * Date the topic can no longer be voted on.
     */
    private Date expiresDate;
    public Date getExpiresDate() { return expiresDate; }
    public void setExpiresDate(Date expiresDate) { this.expiresDate = expiresDate; }

    public long getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(long creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    private long creatorUserId;
}
