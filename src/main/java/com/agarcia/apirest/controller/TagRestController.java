/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.controller;

import com.agarcia.apirest.entity.Tag;
import com.agarcia.apirest.entity.TagRequest;
import com.agarcia.apirest.service.TagService;
import com.agarcia.apirest.utils.ResponseHandler;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api/tags")
public class TagRestController {

    @Autowired
    private TagService tagService;
    
    private static final Logger logger = LoggerFactory.getLogger(TagRestController.class);
    @GetMapping("/")
    public ResponseEntity<Object> findAll() {

        List<Tag> tags = tagService.findAll();
        logger.info("Tag recuperados exitosamente!.");
        return ResponseHandler.generateResponse("Tags recuperados", HttpStatus.OK, tags);
    }

    @GetMapping("/{tagId}")
    public ResponseEntity<Object> getTag(@PathVariable int tagId) {
        Tag tag = tagService.findById(tagId);

        if (tag == null) {
            logger.error("Tag no encontrado!.");
            return ResponseHandler.generateResponse("Tag no encontrado", HttpStatus.NOT_FOUND, null);
        } else {
            logger.info("Tag encontrado!.");
            return ResponseHandler.generateResponse("Tag encontrado!", HttpStatus.OK, tag);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> addTag(@RequestBody TagRequest tagRequest) {
        try {
            Tag tag = new Tag();
            tag.setName(tagRequest.getName());
            tagService.save(tag);
            logger.info("Tag creado exitosamente!");
            return ResponseHandler.generateResponse("Tag creado", HttpStatus.CREATED, tag);
        } catch (Exception e) {
            logger.error("Error al crear el Tag: "+ e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @PutMapping("/{tagId}")
    public ResponseEntity<Object> updateTag(@PathVariable int tagId, @RequestBody TagRequest tagRequest) {
        try {
            Tag tag = tagService.findById(tagId);
            if (tag == null) {
                logger.error("Tag no encontrado!.");
                return ResponseHandler.generateResponse("Tag no encontrado", HttpStatus.NOT_FOUND, null);
            }
            tag.setName(tagRequest.getName());
            tagService.save(tag);
            logger.info("Tag actualizado exitosamente!");
            return ResponseHandler.generateResponse("Tag actualizado", HttpStatus.OK, tag);
        } catch (Exception e) {
            logger.error("Error al actualizar el Tag: "+ e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }
    @DeleteMapping("/{tagId}")
    public ResponseEntity<Object> deteteTag(@PathVariable int tagId) {

        Tag tag = tagService.findById(tagId);

        if (tag == null) {
            logger.error("Tag no encontrado!.");
            return ResponseHandler.generateResponse("Tag no encontrado", HttpStatus.NOT_FOUND, null);
        }

        tagService.deleteById(tagId);
        logger.info("Tag eliminado exitosamente!");
        return ResponseHandler.generateResponse("Tag eliminado", HttpStatus.OK, tag);
    }
}
