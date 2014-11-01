package com.donatespirit.mvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.donatespirit.mvc.model.UserInfo;

//http://www.journaldev.com/3531/spring-mvc-hibernate-mysql-integration-crud-example-tutorial

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class UserInfoDAO {

    @Autowired private SessionFactory sessionFactory;

    @Transactional
    public void addUserInfo(UserInfo userInfo){
        Session session = sessionFactory.getCurrentSession();
        session.persist(userInfo);
    }
}