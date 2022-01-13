/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.service;

import com.agarcia.apirest.entity.UserData;
import java.util.List;

/**
 *
 * @author pacisauctor
 */
public interface UserDataService {
    public List<UserData> findAll();

    public UserData findById(int id);

    public void save(UserData user);

    public void deleteById(int id);

}
