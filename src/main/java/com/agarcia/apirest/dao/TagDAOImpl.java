/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.dao;

import com.agarcia.apirest.entity.Tag;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pacisauctor
 */
@Repository
public class TagDAOImpl implements TagDAO{

    @Autowired
    private EntityManager entityManager;
    
    @Override
    public List<Tag> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        List<Tag> tags = currentSession.createNamedQuery("Tag.findAll").getResultList();
        return tags;
        
    }

    @Override
    public Tag findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query  q = currentSession.createNamedQuery("Tag.findById");
        q.setParameter("id", id);
        List<Tag> lists = q.getResultList();
        if (!lists.isEmpty()) {
            return lists.get(0);
        }
        return null;
    }

    @Override
    public void save(Tag tag) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(tag);
        
    }

    @Override
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query  q = currentSession.createNamedQuery("Tag.findById");
        q.setParameter("id", id);
        List<Tag> lists = q.getResultList();
        if (!lists.isEmpty()) {
            System.out.println("Delete");
            Tag tagToDelete  = lists.get(0);
            currentSession.delete(tagToDelete);
        }
    }
    
}
