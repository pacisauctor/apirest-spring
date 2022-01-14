/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.dao;

import com.agarcia.apirest.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pacisauctor
 */
public interface CategoryDAO {
    public List<Category> findAll();
    
    public Category findById(int id);
    
    @Transactional
    @Modifying
    public void save(Category category);
    
    @Transactional
    @Modifying
    public void deleteById(int id);
    
}
