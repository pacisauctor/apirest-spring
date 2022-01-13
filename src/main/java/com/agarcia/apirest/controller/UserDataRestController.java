/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agarcia.apirest.entity.UserData;
import com.agarcia.apirest.service.UserDataService;
/**
 *
 * @author pacisauctor
 */
@RestController
@RequestMapping("/api")
public class UserDataRestController {
    @Autowired
    private UserDataService userDataService;
    
    @GetMapping("/users")
    public List<UserData> findAll(){
        return userDataService.findAll();
    }
    @GetMapping("/")
    public String welcome(){
        return "hello world";
    }
}
