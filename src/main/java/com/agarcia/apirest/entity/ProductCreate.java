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
public class ProductCreate {
    private String name;
    private double cost;
    private double price;
    private int categoryId;
    private int [] tagsId;

    public ProductCreate(String name, double cost, double price, int categoryId, int[] tagsId) {
        this.name = name;
        this.cost = cost;
        this.price = price;
        this.categoryId = categoryId;
        this.tagsId = tagsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int[] getTagsId() {
        return tagsId;
    }

    public void setTagsId(int[] tagsId) {
        this.tagsId = tagsId;
    }

 
    
    
}
