/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pacisauctor
 */
@RestController

public class HomeController {
    
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @GetMapping("/")
    public String welcome(){
        logger.info("Se accedió al inicio");
        return "<h1>Visita la <a href='https://documenter.getpostman.com/view/12356617/UVXjJvYr#8c8f3d8c-163c-4a65-8a82-f0ed8d927ff4'>Documenteción de postman<a/> :D<h1/>";
    }
}