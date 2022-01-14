/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.service;

import com.agarcia.apirest.entity.Product;
import java.util.List;

/**
 *
 * @author pacisauctor
 */
public interface ProductService {

    public List<Product> findAll();

    public Product findById(int id);

    public void save(Product category);

    public void deleteById(int id);
}
