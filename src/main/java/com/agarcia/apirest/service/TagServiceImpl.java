/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.service;

import com.agarcia.apirest.dao.TagDAO;
import com.agarcia.apirest.entity.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pacisauctor
 */
@Service
public class TagServiceImpl implements TagService{

    
    @Autowired
    private TagDAO tagDAO;
    
    @Override
    public List<Tag> findAll() {
        List<Tag> tags = tagDAO.findAll();
        return tags;
    }

    @Override
    public Tag findById(int id) {
        return tagDAO.findById(id);
    }

    @Override
    public void save(Tag tag) {
        tagDAO.save(tag);
    }

    @Override
    public void deleteById(int id) {
        tagDAO.deleteById(id);
    }
    
}
