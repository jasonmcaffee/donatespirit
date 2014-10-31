package com.donatespirit.mvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.donatespirit.mvc.model.User;

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

//    @Transactional
//    public Person getPersonById(int id) {
//        Session session = this.sessionFactory.getCurrentSession();
//        Person p = (Person) session.load(Person.class, new Integer(id));
//        logger.info("Person loaded successfully, Person details="+p);
//        return p;
//    }
}