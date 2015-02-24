package com.donatespirit.mvc.dao;

import com.donatespirit.mvc.model.CreateVoteTopic;
import com.donatespirit.mvc.model.VoteTopic;
import com.donatespirit.mvc.model.UserVote;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * All voting related db interaction.
 * Primarily deals with VoteTopic and UserVote
 */
@Repository
public class VoteDAO {
    @Autowired
    private SessionFactory sessionFactory;


    /**
     * Get all vote topics by grouping by the voteId, and summing the votes in UserVote
     * @return
     */
    @Transactional
    public List<VoteTopic> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<VoteTopic> voteTopics = session.createSQLQuery("select vt.id, vt.date, vt.topic, vt.expiresDate, vt.creatorUserId, User.userName as creatorUserName, " +
               "COALESCE(SUM(UserVote.vote), 0) as yesVotes, " +
                "SUM(CASE WHEN UserVote.vote = 0 THEN 1 ELSE 0 END) as noVotes, " +
               "count(UserVote.vote) as totalVotes " +
                "from VoteTopic vt " +
                "left outer join UserVote on vt.id = UserVote.voteTopicId " +
                "join User on vt.creatorUserId = User.id " +
                "group by vt.id " +
                "order by vt.date desc ").addEntity(VoteTopic.class).setMaxResults(500).list();


       // List<VoteTopic> voteTopics = session.createSQLQuery( "select * from VoteTopic").list(); //.setMaxResults(500).
        return voteTopics;
    }

    @Transactional
    public VoteTopic getVoteTopicById(long voteTopicId) {
        Session session = sessionFactory.getCurrentSession();
        List<VoteTopic> voteTopics = session.createSQLQuery("select vt.id, vt.date, vt.topic, vt.expiresDate,  vt.creatorUserId, User.userName as creatorUserName,  " +
                "COALESCE(SUM(UserVote.vote), 0) as yesVotes, " +
                "SUM(CASE WHEN UserVote.vote = 0 THEN 1 ELSE 0 END) as noVotes, " +
                "count(UserVote.vote) as totalVotes " +
                "from VoteTopic vt " +
                "left outer join UserVote on vt.id = UserVote.voteTopicId " +
                "join User on vt.creatorUserId = User.id " +
                "where vt.id = :voteTopicId " +
                "group by vt.id")
                .addEntity(VoteTopic.class)
                //.setString("voteTopicId", Long.toString(voteTopicId))
                .setParameter("voteTopicId", voteTopicId)
                .list();


        return voteTopics.get(0);
    }

    /**
     * Save the users vote to the db.
     * @param userVote
     */
    @Transactional
    public void castVote(UserVote userVote){
        userVote.setDate(new Date());
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(userVote);
    }

    /**
     * Finds the vote for the user on the topic.
     * @param voteTopicId
     * @param userId
     * @return - null if no entry found
     */
    @Transactional
    public UserVote getUserVoteById(long voteTopicId, long userId){
        List<UserVote> userVotes = sessionFactory.getCurrentSession().createSQLQuery(
                "select * from UserVote where voteTopicId = :voteTopicId and userId = :userId")
                .addEntity(UserVote.class)
                .setParameter("voteTopicId", voteTopicId)
                .setParameter("userId", userId)
                .list();
        UserVote fromDbUserVote = null;
        if(userVotes.size() > 0){
            fromDbUserVote = userVotes.get(0);
        }
        return fromDbUserVote;
    }

    /**
     *
     * @param voteTopic
     */
    @Transactional
    public void createVoteTopic(CreateVoteTopic voteTopic){
        voteTopic.setDate(new Date());
        Date expirationDate = new Date();
        //expire 5 days from today.
        expirationDate.setTime(voteTopic.getDate().getTime() + 5 * 24 * 60 *60 * 1000);
        voteTopic.setExpiresDate(expirationDate);

        Session session = sessionFactory.getCurrentSession();
        session.persist(voteTopic);
    }
}
