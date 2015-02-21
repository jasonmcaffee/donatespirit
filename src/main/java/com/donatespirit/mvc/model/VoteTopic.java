package com.donatespirit.mvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Represents a topic users can vote on. E.g. "should member X get promoted to co leader?"
 * Votes in the UserVote table will be joined with this table.
 */
@Entity
public class VoteTopic {
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

    private int yesVotes;

    public int getYesVotes() {
        return yesVotes;
    }

    public void setYesVotes(int yesVotes) {
        this.yesVotes = yesVotes;
    }

    public int getNoVotes() {
        return noVotes;
    }

    public void setNoVotes(int noVotes) {
        this.noVotes = noVotes;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }

    private int noVotes;
    private int totalVotes;

}
