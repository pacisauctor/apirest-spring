/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.dao;

import com.agarcia.apirest.entity.Category;
import com.agarcia.apirest.entity.Product;
import com.agarcia.apirest.entity.ProductCreate;
import com.agarcia.apirest.entity.Tag;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public Product save(ProductCreate product) {
        Session currentSession = entityManager.unwrap(Session.class);
        Product newProduct = product.convert();
        Category category = getCategory(product.getCategoryId());
        newProduct.setIdCategory(category);
        List<Tag> tags = new ArrayList<>();

        for (int i = 0; i < product.getTagsId().length; i++) {
            Tag tag = getTag(product.getTagsId()[i]);
            tags.add(tag);
        }
        newProduct.setIsActive(product.isIsActive());
        newProduct.setTagList(tags);
        newProduct.setDateCreated(new Date());
        newProduct.setLastModified(new Date());
        currentSession.saveOrUpdate(newProduct);
        return newProduct;
    }

    private Category getCategory(int idCategory) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createNamedQuery("Category.findById");
        query.setParameter("id", idCategory);
        List<Category> lists = query.getResultList();
        if (lists.isEmpty()) {
            try {
                throw new Exception("Categoria does not exist");
            } catch (Exception ex) {
                Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Category category = lists.get(0);
        return category;
    }

    private Tag getTag(int idTag) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query q = currentSession.createNamedQuery("Tag.findById");
        q.setParameter("id", idTag);
        List<Tag> results = q.getResultList();
        if (results.isEmpty()) {
            try {
                throw new Exception("Tag does not exist");
            } catch (Exception ex) {
                Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Tag tag = results.get(0);
        return tag;
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

    @Override
    public Product save(ProductCreate productData, Product product) {
        Session currentSession = entityManager.unwrap(Session.class);

        product.setName(productData.getName());
        product.setPrice(BigDecimal.valueOf(productData.getPrice()));
        product.setCost(BigDecimal.valueOf(productData.getCost()));
        product.setIsActive(productData.isIsActive());
        if (product.getIdCategory().getId() == productData.getCategoryId()) {
            System.out.println("Misma categoría, nada que editar");
        } else {
            Category c = getCategory(productData.getCategoryId());
            product.setIdCategory(c);
        }
        List<Tag> tags = new ArrayList<>();

        for (int i = 0; i < productData.getTagsId().length; i++) {
            Tag tag = getTag(productData.getTagsId()[i]);
            tags.add(tag);
        }
        product.setTagList(tags);
        currentSession.saveOrUpdate(product);
        return product;
    }

}
