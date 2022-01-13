/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.agarcia.apirest.dao;

import com.agarcia.apirest.entity.UserData;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pacisauctor
 */
public interface UserDataDAO {

    public List<UserData> findAll();

    public UserData findById(int id);
    
    @Transactional
    @Modifying
    public void save(UserData user);

    @Transactional
    @Modifying
    public void deleteById(int id);

}
