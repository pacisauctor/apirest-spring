/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.service;

import com.agarcia.apirest.dao.UserDAO;
import com.agarcia.apirest.dao.UserDataDAO;
import com.agarcia.apirest.entity.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pacisauctor
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserData login(String username, String password) {
        return userDAO.login(username, password);
    }
    
}
