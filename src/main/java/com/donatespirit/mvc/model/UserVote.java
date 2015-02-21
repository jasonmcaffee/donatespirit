package com.donatespirit.mvc.model;

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

    private int voteTopicID;

    public int getVoteTopicID() {
        return voteTopicID;
    }

    public void setVoteTopicID(int voteTopicID) {
        this.voteTopicID = voteTopicID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    private int userId;
    private boolean vote;

}
