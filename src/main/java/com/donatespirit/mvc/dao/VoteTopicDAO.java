package com.donatespirit.mvc.dao;

import com.donatespirit.mvc.model.VoteTopic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jason on 2/16/15.
 */
@Repository
public class VoteTopicDAO {
    @Autowired
    private SessionFactory sessionFactory;


    @Transactional
    public List<VoteTopic> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<VoteTopic> voteTopics = session.createSQLQuery("select vt.id, vt.date, vt.topic, vt.expiresDate, " +
               "SUM(UserVote.vote) as yesVotes, " +
                "SUM(CASE WHEN UserVote.vote = 0 THEN 1 ELSE 0 END) as noVotes, " +
               "count(UserVote.vote) as totalVotes " +
                "from VoteTopic vt " +
                "join UserVote on vt.id = UserVote.voteTopicId " +
                "group by vt.id").addEntity(VoteTopic.class).setMaxResults(500).list();


       // List<VoteTopic> voteTopics = session.createSQLQuery( "select * from VoteTopic").list(); //.setMaxResults(500).
        return voteTopics;
    }
}
