package com.donatespirit.mvc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.donatespirit.mvc.model.User;

//http://www.journaldev.com/3531/spring-mvc-hibernate-mysql-integration-crud-example-tutorial

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class UserDAO {

    @Autowired private SessionFactory sessionFactory;

    /**
     * @Transactional annotation below will trigger Spring Hibernate transaction manager to automatically create
     * a hibernate session. See src/main/webapp/WEB-INF/servlet-context.xml
     */
    @Transactional
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<User> users = session.createQuery("from User").list();
        return users;
    }

    @Transactional
    public User getUserByUserName(String userName){
        User user = null;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where userName= :name");
        query.setParameter("name", userName);
        List<User> users = query.list();
        if(users.isEmpty()){
            //no match
        }else{
            user = users.get(0);
        }
        return user;
    }

    @Transactional
    public void addUser(User user){
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Transactional
    public void updateUser(User user){
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Transactional
    public void removeUser(User user){
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }

//    @Transactional
//    public Person getPersonById(int id) {
//        Session session = this.sessionFactory.getCurrentSession();
//        Person p = (Person) session.load(Person.class, new Integer(id));
//        logger.info("Person loaded successfully, Person details="+p);
//        return p;
//    }
}