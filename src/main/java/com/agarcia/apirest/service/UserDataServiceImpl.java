/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.service;

import com.agarcia.apirest.dao.UserDataDAO;
import com.agarcia.apirest.entity.UserData;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pacisauctor
 */
@Service
public class UserDataServiceImpl implements UserDataService {
    
    @Autowired
    private UserDataDAO userDataDAO;

    @Override
    public List<UserData> findAll() {
        List<UserData> listUsers = userDataDAO.findAll();
        return listUsers;
    }

    @Override
    public UserData findById(int id) {
        return userDataDAO.findById(id);
    }

    @Override
    public void save(UserData user) {
        userDataDAO.save(user);
    }

    @Override
    public void deleteById(int id) {
        userDataDAO.deleteById(id);
    }
    
}
