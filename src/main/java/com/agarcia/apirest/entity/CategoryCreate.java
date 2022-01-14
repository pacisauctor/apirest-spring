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
public class CategoryCreate {
    private String name;

    public CategoryCreate() {
    }

    public CategoryCreate(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Category convert(){
        Category c = new Category();
        c.setName(name);
        return c;
    }
    
}
