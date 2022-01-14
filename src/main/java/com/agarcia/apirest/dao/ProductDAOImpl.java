/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.dao;

import com.agarcia.apirest.entity.Category;
import com.agarcia.apirest.entity.Product;
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
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Product> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        List<Product> res = currentSession.createNamedQuery("Product.findAll").getResultList();
        return res;
    }

    @Override
    public Product findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createNamedQuery("Product.findById");
        query.setParameter("id", id);
        List<Product> lists = query.getResultList();
        if (!lists.isEmpty()) {
            return lists.get(0);
        }
        return null;
    }

    @Override
    public void save(Product product) {
        Session currentSession = entityManager.unwrap(Session.class);
        product.setLastModified(new Date());
        currentSession.saveOrUpdate(product);
    }

    @Override
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createNamedQuery("Product.findById");
        query.setParameter("id", id);
        List<Product> lists = query.getResultList();
        if (!lists.isEmpty()) {
            Product res = lists.get(0);
            res.setIsActive(Boolean.FALSE);
            res.setLastModified(new Date());
            currentSession.saveOrUpdate(res);
        }
    }

}
