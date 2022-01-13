/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.agarcia.apirest.dao;

import com.agarcia.apirest.entity.UserData;
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
public class UserDataDAOImpl implements UserDataDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UserData> findAll() {
        // session
        Session currentSession = entityManager.unwrap(Session.class);
        List<UserData> res =  currentSession.createNamedQuery("UserData.findAll").getResultList();
        return res;
    }

    @Override
    public UserData findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query =  currentSession.createNamedQuery("UserData.findById");
        query.setParameter("id", id);
        List<UserData> lists = query.getResultList();
        if(!lists.isEmpty()){
            return lists.get(0);
        }
        return null;
    }

    @Override
    public void save(UserData user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(user);  
    }

    @Override
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query =  currentSession.createNamedQuery("UserData.findById");
        query.setParameter("id", id);
        List<UserData> lists = query.getResultList();
        if(!lists.isEmpty()){
            UserData res =  lists.get(0);
            res.setIsActive(Boolean.FALSE);
            currentSession.saveOrUpdate(res);
        }
        
    }

}
