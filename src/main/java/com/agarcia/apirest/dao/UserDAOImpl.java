/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.dao;

import com.agarcia.apirest.entity.UserData;
import com.agarcia.apirest.utils.EncriptadorAES;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
            
            try {
                UserData usuario = (UserData) query.getResultList().get(0);
                System.out.println(password);
                String passwordDatabaseDesencript = new EncriptadorAES().desencriptar(usuario.getPassword(), "mySecretKeyIsVerySecret");
                System.out.println(passwordDatabaseDesencript);
                if(passwordDatabaseDesencript == null ? password == null : passwordDatabaseDesencript.equals(password)){
                    System.out.println("Actualizando usuario");
                    Query q = currentSession.createNamedQuery("UserData.updateLastAccess");
                    q.setParameter("id", usuario.getId());
                    int executeUpdate = q.executeUpdate();
                    
                    return usuario;
                }else{
                    System.out.println("credenciales incorrectas");
                    
                }
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

}
