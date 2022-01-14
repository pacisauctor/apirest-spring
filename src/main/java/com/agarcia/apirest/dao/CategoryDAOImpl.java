/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.dao;

import com.agarcia.apirest.entity.Category;
import java.util.Date;
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
public class CategoryDAOImpl implements CategoryDAO{

    @Autowired
    private EntityManager entityManager;
    
    @Override
    public List<Category> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        List<Category> res = currentSession.createNamedQuery("Category.findAll").getResultList();
        return res;
    }

    @Override
    public Category findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query =  currentSession.createNamedQuery("Category.findById");
        query.setParameter("id", id);
        List<Category> lists = query.getResultList();
        if(!lists.isEmpty()){
            return lists.get(0);
        }
        return null;
    }

    @Override
    public void save(Category category) {
        Session currentSession = entityManager.unwrap(Session.class);
        category.setLastModified(new Date());
        currentSession.saveOrUpdate(category);
    }

    @Override
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query =  currentSession.createNamedQuery("Category.findById");
        query.setParameter("id", id);
        List<Category> lists = query.getResultList();
        System.out.println("antes de eliminar");
        if(!lists.isEmpty()){
            System.out.println("eliminando");
            Category res =  lists.get(0);
            res.setIsActive(Boolean.FALSE);
            res.setLastModified(new Date());
            currentSession.saveOrUpdate(res);
        }
    }
    
}
