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
public class UserDataRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private boolean isAdmin;

    public UserDataRequest(String username, String firstName, String lastName, String password, boolean isAdmin) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    
    public UserDataRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    public UserData convert(){
        UserData object = new UserData();
        
        object.setUsername(username);
        object.setFirstName(firstName);
        object.setLastName(lastName);
        object.setIsAdmin(isAdmin);
        object.setPassword(password);       
        return object;
    }

    
}
