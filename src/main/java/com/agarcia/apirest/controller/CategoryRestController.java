/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.controller;

import com.agarcia.apirest.entity.Category;
import com.agarcia.apirest.entity.CategoryCreate;
import com.agarcia.apirest.entity.CategoryUpdate;
import com.agarcia.apirest.entity.UserData;
import com.agarcia.apirest.entity.UserDataRequest;
import com.agarcia.apirest.service.CategoryService;
import com.agarcia.apirest.utils.ResponseHandler;
import java.util.Date;
import java.util.List;
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
@RequestMapping("/api/categories")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<Object> findAll() {

        List<Category> categories = categoryService.findAll();
        return ResponseHandler.generateResponse("Categorias recuperadas", HttpStatus.OK, categories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Object> getCategory(@PathVariable int categoryId) {
        Category category = categoryService.findById(categoryId);

        if (category == null) {
            return ResponseHandler.generateResponse("Categoria no encontrada", HttpStatus.NOT_FOUND, null);
        } else {
            return ResponseHandler.generateResponse("Categoria encontrada!", HttpStatus.OK, category);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> addUser(@RequestBody CategoryCreate categoryRequest) {
        try {
            Category category = categoryRequest.convert();
            category.setDateCreated(new Date());
            category.setLastModified(new Date());
            category.setIsActive(Boolean.TRUE);
            category.setIsErased(Boolean.FALSE);
            categoryService.save(category);
            return ResponseHandler.generateResponse("Categoria creada", HttpStatus.CREATED, category);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Object> updateCategory(@PathVariable int categoryId, @RequestBody CategoryUpdate categoryRequest) {
        try {
            Category category = categoryService.findById(categoryId);
            if(category == null){
                return ResponseHandler.generateResponse("Categoría no encontrada", HttpStatus.NOT_FOUND, null);
            }
            categoryRequest.copyAttributes(category);
            categoryService.save(category);
            return ResponseHandler.generateResponse("Categoria actualizada", HttpStatus.OK, category);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Object> deteteUser(@PathVariable int categoryId) {

        Category category = categoryService.findById(categoryId);

        if (category == null) {
            return ResponseHandler.generateResponse("Categoria no encontrada", HttpStatus.NOT_FOUND, null);
        }

        categoryService.deleteById(categoryId);

        return ResponseHandler.generateResponse("Categoria eliminada", HttpStatus.OK, category);
    }
}
