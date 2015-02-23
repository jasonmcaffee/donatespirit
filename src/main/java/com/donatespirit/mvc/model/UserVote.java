package com.donatespirit.mvc.model;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Represents a vote cast by a user for a VoteTopic.
 * Users can have only 1 vote per topic (enforced by unique constraint of userId and voteTopicId on UserVote table)
 */
@Entity
public class UserVote {

    @Id
    @GeneratedValue
    private long id;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    /**
     * The date the vote was cast or recast
     */
    private Date date;
    public Date getDate() {  return date;  }
    public void setDate(Date date) {  this.date = date; }

    private int voteTopicId;

    public int getVoteTopicId() {
        return voteTopicId;
    }

    public void setVoteTopicId(int voteTopicId) {
        this.voteTopicId = voteTopicId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean getVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    private long userId;

    //@Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean vote;

}
