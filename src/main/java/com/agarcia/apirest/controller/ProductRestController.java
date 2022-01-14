/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.controller;

import com.agarcia.apirest.entity.ProductCreate;
import com.agarcia.apirest.entity.Product;
import com.agarcia.apirest.service.ProductService;
import com.agarcia.apirest.utils.ResponseHandler;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pacisauctor
 */
@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<Object> findAll() {

        List<Product> products = productService.findAll();
        return ResponseHandler.generateResponse("Categorias recuperadas", HttpStatus.OK, products);
    }

    /**
     * Get a product given 
     * @param productId
     * @return
     */
    @GetMapping("/{productId}")
    public ResponseEntity<Object> getProduct(@PathVariable int productId) {
        Product product = productService.findById(productId);

        if (product == null) {
            return ResponseHandler.generateResponse("Producto no encontrado", HttpStatus.NOT_FOUND, null);
        } else {
            return ResponseHandler.generateResponse("Producto encontrado!", HttpStatus.OK, product);
        }
    }
    @PostMapping("/")
    public ResponseEntity<Object> addProduct(@RequestBody ProductCreate productRequest) {
        try {
            
            productService.save(productRequest);
            return ResponseHandler.generateResponse("Producto creado", HttpStatus.CREATED, product);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }
}
