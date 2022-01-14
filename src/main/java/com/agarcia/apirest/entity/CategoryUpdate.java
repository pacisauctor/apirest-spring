/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.entity;

import java.util.Date;

/**
 *
 * @author pacisauctor
 */
public class CategoryUpdate {
    private String name;
    private boolean isActive;
    private boolean isErased;

    public CategoryUpdate(String name, boolean isActive, boolean isErased) {
        this.name = name;
        this.isActive = isActive;
        this.isErased = isErased;
    }

    public CategoryUpdate() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isIsErased() {
        return isErased;
    }

    public void setIsErased(boolean isErased) {
        this.isErased = isErased;
    }

    public void copyAttributes(Category category) {
        category.setLastModified(new Date());
        category.setName(name);
        category.setIsActive(isActive);
        category.setIsErased(isErased);
    }
    
}
