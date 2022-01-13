/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.service;

import com.agarcia.apirest.entity.UserData;

/**
 *
 * @author pacisauctor
 */
public interface UserService {
    public UserData login(String username, String password);
}
