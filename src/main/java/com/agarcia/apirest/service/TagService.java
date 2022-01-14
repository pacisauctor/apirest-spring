/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.service;

import com.agarcia.apirest.entity.Tag;
import java.util.List;

/**
 *
 * @author pacisauctor
 */
public interface TagService {

    public List<Tag> findAll();

    public Tag findById(int id);

    public void save(Tag tag);

    public void deleteById(int id);
}
