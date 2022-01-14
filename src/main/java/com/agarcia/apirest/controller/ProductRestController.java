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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    private static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);
    
    @GetMapping("/")
    public ResponseEntity<Object> findAll() {

        List<Product> products = productService.findAll();
        logger.info("Productos recuperados exitosamente.");
        return ResponseHandler.generateResponse("Productos recuperados", HttpStatus.OK, products);
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
            logger.error("Productos con id " +productId+" no encontrado.");
            return ResponseHandler.generateResponse("Producto no encontrado", HttpStatus.NOT_FOUND, null);
        } else {
            logger.info("Productos encontrado exitosamente.");
            return ResponseHandler.generateResponse("Producto encontrado!", HttpStatus.OK, product);
        }
    }
    @PostMapping("/")
    public ResponseEntity<Object> addProduct(@RequestBody ProductCreate productRequest) {
        try {
            
            Product save = productService.save(productRequest);
            logger.info("Producto creado exitosamente.");
            return ResponseHandler.generateResponse("Producto creado", HttpStatus.CREATED, save);
        } catch (Exception e) {
            logger.error("Productos no creado, error en ejecución: " + e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }
    @PutMapping("/{productId}")
    public ResponseEntity<Object> updateProduct(@PathVariable int productId, @RequestBody ProductCreate productRequest) {
        try {
            Product product = productService.findById(productId);
            if(product == null){
                logger.error("Productos con id " +productId+" no encontrado.");
                return ResponseHandler.generateResponse("Producto no encontrado", HttpStatus.NOT_FOUND, null);
            }
            productService.save(productRequest, product);
            logger.info("Producto actualizado exitosamente.");
            return ResponseHandler.generateResponse("Producto actualizado", HttpStatus.OK, product);
        } catch (Exception e) {
            logger.error("Productos no actualizado, error en ejecución: " + e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int productId) {

        Product product = productService.findById(productId);

        if (product == null) {
            logger.error("Productos con id " +productId+" no encontrado.");
            return ResponseHandler.generateResponse("Producto no encontrado", HttpStatus.NOT_FOUND, null);
        }

        productService.deleteById(productId);
        logger.info("Producto eliminado exitosamente.");
        return ResponseHandler.generateResponse("Tag eliminado", HttpStatus.OK, product);
    }
    
    
}
