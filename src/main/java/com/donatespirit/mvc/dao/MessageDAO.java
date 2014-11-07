package com.donatespirit.mvc.dao;

import com.donatespirit.mvc.model.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.Transaction;
import java.util.Date;
import java.util.List;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class MessageDAO {

    @Autowired private SessionFactory sessionFactory;

    @Transactional
    public void addMessage(Message message){
        Session session = sessionFactory.getCurrentSession();
        message.setDate(new Date());
        session.persist(message);
    }

    @Transactional
    public List<Message> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Message> messages = session.createQuery("from Message").list();
        return messages;
    }
}
