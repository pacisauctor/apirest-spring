/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.dao;

import com.agarcia.apirest.entity.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pacisauctor
 */
public interface TagDAO {

    public List<Tag> findAll();

    public Tag findById(int id);

    @Transactional
    @Modifying
    public void save(Tag tag);

    @Transactional
    @Modifying
    public void deleteById(int id);
}
