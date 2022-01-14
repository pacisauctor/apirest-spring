/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agarcia.apirest.entity.UserData;
import com.agarcia.apirest.entity.UserDataRequest;
import com.agarcia.apirest.service.UserDataService;
import com.agarcia.apirest.utils.ResponseHandler;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author pacisauctor
 */
@RestController
@RequestMapping("/api/users")
public class UserDataRestController {

    @Autowired
    private UserDataService userDataService;

    private static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);
    
    @GetMapping("/")
    public ResponseEntity<Object> findAll() {

        List<UserData> users = userDataService.findAll();
        logger.info("Usuarios recuperados exitosamente!.");
        return ResponseHandler.generateResponse("Usuarios recuperados", HttpStatus.OK, users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable int userId) {
        UserData user = userDataService.findById(userId);

        if (user == null) {
            logger.error("Usuario no encontrado!.");
            return ResponseHandler.generateResponse("Usuario no encontrado", HttpStatus.NOT_FOUND, null);
        } else {
            logger.info("Usuario encontrado!");
            return ResponseHandler.generateResponse("Usuario encontrado!", HttpStatus.OK, user);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> addUser(@RequestBody UserDataRequest userRequest) {
        try {
            UserData user = userRequest.convert();
            user.setDateCreated(new Date());
            user.setIsActive(Boolean.TRUE);
            userDataService.save(user);
            logger.info("Usuario creado exitosamente!.");
            return ResponseHandler.generateResponse("Usuario creado", HttpStatus.CREATED, user);
        } catch (Exception e) {
            logger.error("Error al crear el Usuario: "+ e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable int userId, @RequestBody UserDataRequest userRequest) {
        try {
            UserData user = userDataService.findById(userId);
            if(user == null){
                logger.error("Usuario no encontrado!.");
                return ResponseHandler.generateResponse("Usuario no encontrado", HttpStatus.NOT_FOUND, null);
            }
            userRequest.copyAttributes(user);
            userDataService.save(user);
            logger.info("Usuario actualizado exitosamente!.");
            return ResponseHandler.generateResponse("Usuario actualizado", HttpStatus.OK, user);
        } catch (Exception e) {
            logger.error("Error al actualizar el Usuario: "+ e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }
    @GetMapping("/{userId}/activate")
    public ResponseEntity<Object> activateUser(@PathVariable int userId) {
        try {
            UserData user = userDataService.findById(userId);
            if(user == null){
                logger.error("Usuario no encontrado!.");
                return ResponseHandler.generateResponse("Usuario no encontrado", HttpStatus.NOT_FOUND, null);
            }
            user.setIsActive(Boolean.TRUE);
            userDataService.save(user);
            logger.info("Usuario activado exitosamente!.");
            return ResponseHandler.generateResponse("Usuario activado", HttpStatus.OK, user);
        } catch (Exception e) {
            logger.error("Error al activar el Usuario: "+ e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deteteUser(@PathVariable int userId) {

        UserData user = userDataService.findById(userId);

        if (user == null) {
            logger.error("Usuario no encontrado!.");
            return ResponseHandler.generateResponse("Usuario no encontrado", HttpStatus.NOT_FOUND, null);
        }

        userDataService.deleteById(userId);
        logger.info("Usuario desactivado exitosamente!.");
        return ResponseHandler.generateResponse("Usuario eliminado", HttpStatus.OK, user);
    }

}
