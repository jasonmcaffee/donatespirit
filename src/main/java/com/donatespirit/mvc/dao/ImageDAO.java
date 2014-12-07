package com.donatespirit.mvc.dao;

import com.donatespirit.mvc.model.Image;
import com.donatespirit.mvc.model.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class ImageDAO {
    @Autowired private SessionFactory sessionFactory;

    @Transactional
    public void addImage(Image image){
        Session session = sessionFactory.getCurrentSession();
        image.setDate(new Date());
        session.persist(image);
    }

    @Transactional
    public List<Image> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Image> images = session.createQuery("from Image order by date desc").setMaxResults(500).list();
        return images;
    }
}
