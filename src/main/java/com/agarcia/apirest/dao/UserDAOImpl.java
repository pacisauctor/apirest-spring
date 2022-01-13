/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class UserDAOImpl implements UserDAO {

    @Autowired
    private EntityManager entityManager;
    
    @Override
    public UserData login(String username, String password) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createNamedQuery("UserData.findByUsername");
        query.setParameter("username", username);
        if (!query.getResultList().isEmpty()){
            
            UserData usuario = (UserData) query.getResultList().get(0);
            if(usuario.getPassword() == null ? password == null : usuario.getPassword().equals(password)){
                System.out.println("Actualizando usuario");
                Query q = currentSession.createNamedQuery("UserData.updateLastAccess");
                q.setParameter("id", usuario.getId());
                int executeUpdate = q.executeUpdate();
                
                return usuario;
            }else{
                System.out.println("no funcionó");
                
            }
        }
        return null;
    }

}
