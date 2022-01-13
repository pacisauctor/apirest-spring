/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.entity;

/**
 *
 * @author pacisauctor
 */
public class Login {
    String user;
    String password;

    public Login(String user, String password) {
        this.user = user;
        this.password = password;
    }

    
    
    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
    
}
