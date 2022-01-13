/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agarcia.apirest.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pacisauctor
 */
@Entity
@Table(name = "user_data", catalog = "app_database", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"username"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserData.findAll", query = "SELECT u FROM UserData u"),
    @NamedQuery(name = "UserData.updateLastAccess", query = "UPDATE UserData SET lastAccess=GETDATE() WHERE id = :id"),
    @NamedQuery(name = "UserData.findById", query = "SELECT u FROM UserData u WHERE u.id = :id"),
    @NamedQuery(name = "UserData.findByUsername", query = "SELECT u FROM UserData u WHERE u.username = :username"),
    @NamedQuery(name = "UserData.findByFirstName", query = "SELECT u FROM UserData u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "UserData.findByLastName", query = "SELECT u FROM UserData u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "UserData.findByPassword", query = "SELECT u FROM UserData u WHERE u.password = :password"),
    @NamedQuery(name = "UserData.findByToken", query = "SELECT u FROM UserData u WHERE u.token = :token"),
    @NamedQuery(name = "UserData.findByDateCreated", query = "SELECT u FROM UserData u WHERE u.dateCreated = :dateCreated"),
    @NamedQuery(name = "UserData.findByLastAccess", query = "SELECT u FROM UserData u WHERE u.lastAccess = :lastAccess"),
    @NamedQuery(name = "UserData.findByIsAdmin", query = "SELECT u FROM UserData u WHERE u.isAdmin = :isAdmin"),
    @NamedQuery(name = "UserData.findByIsActive", query = "SELECT u FROM UserData u WHERE u.isActive = :isActive")})
public class UserData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String username;
    @Basic(optional = false)
    @Column(name = "first_name", nullable = false, length = 40)
    private String firstName;
    @Basic(optional = false)
    @Column(name = "last_name", nullable = false, length = 40)
    private String lastName;
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String password;
    @Column(length = 250)
    private String token;
    @Column(name = "date_created", columnDefinition="DATETIME DEFAULT GETDATE()")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "last_access")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastAccess;
    @Column(name = "is_admin", columnDefinition = "DEFAULT 0")
    private Boolean isAdmin;
    @Column(name = "is_active", columnDefinition = "DEFAULT 1")
    private Boolean isActive;

    public UserData() {
    }

    public UserData(Integer id) {
        this.id = id;
    }

    public UserData(Integer id, String username, String firstName, String lastName, String password) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserData)) {
            return false;
        }
        UserData other = (UserData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.agarcia.apirest.service.UserData[ id=" + id + " ]";
    }
    
}
