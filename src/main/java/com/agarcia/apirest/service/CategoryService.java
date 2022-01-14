/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.service;

import com.agarcia.apirest.entity.Category;
import java.util.List;

/**
 *
 * @author pacisauctor
 */
public interface CategoryService {

    public List<Category> findAll();

    public Category findById(int id);

    public void save(Category category);

    public void deleteById(int id);
}
