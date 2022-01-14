/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.controller;

import com.agarcia.apirest.entity.Login;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agarcia.apirest.entity.UserData;
import com.agarcia.apirest.service.UserDataService;
import com.agarcia.apirest.service.UserService;
import com.agarcia.apirest.utils.ResponseHandler;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author pacisauctor
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDataService userDataService;
    
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Login loginData) {
        try {
            String token = getJWTToken(loginData.getUser());
            UserData res = userService.login(loginData.getUser(), loginData.getPassword());
            if (!res.getIsActive()) {
                logger.warn("El usuario que quiso acceder está inactivo, por lo que no puede iniciar sesión");
                return ResponseHandler.generateResponse("Usuario inactivo, no se puede iniciar sesión!!", HttpStatus.OK, res);
            }
            res.setToken(token);
            res.setLastAccess(new Date());
            userDataService.save(res);
            logger.info("Inicio de sesión de "+ loginData.getUser() +" exitoso");
            return ResponseHandler.generateResponse("Autenticado!!", HttpStatus.OK, res);
        } catch (NullPointerException e) {
            logger.error("Credenciales incorrectas, intente nuevamente");
            return ResponseHandler.generateResponse("Credenciales incorrecta!!", HttpStatus.BAD_REQUEST, null);
        }
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("agarcia")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000)) // 10 minutos
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
