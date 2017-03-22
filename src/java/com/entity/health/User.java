/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity.health;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByAge", query = "SELECT u FROM User u WHERE u.age = :age"),
    @NamedQuery(name = "User.findByHeight", query = "SELECT u FROM User u WHERE u.height = :height"),
    @NamedQuery(name = "User.findByWeight", query = "SELECT u FROM User u WHERE u.weight = :weight"),
    @NamedQuery(name = "User.findByGender", query = "SELECT u FROM User u WHERE u.gender = :gender"),
    @NamedQuery(name = "User.findByActivityLevel", query = "SELECT u FROM User u WHERE u.activityLevel = :activityLevel"),
    @NamedQuery(name = "User.findByStepPerMile", query = "SELECT u FROM User u WHERE u.stepPerMile = :stepPerMile")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Age")
    private int age;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Height")
    private double height;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Weight")
    private double weight;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Gender")
    private int gender;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ActivityLevel")
    private int activityLevel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "StepPerMile")
    private double stepPerMile;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Userreport> userreportCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Userfood> userfoodCollection;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name, int age, double height, double weight, int gender, int activityLevel, double stepPerMile) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.activityLevel = activityLevel;
        this.stepPerMile = stepPerMile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }

    public double getStepPerMile() {
        return stepPerMile;
    }

    public void setStepPerMile(double stepPerMile) {
        this.stepPerMile = stepPerMile;
    }

    @XmlTransient
    public Collection<Userreport> getUserreportCollection() {
        return userreportCollection;
    }

    public void setUserreportCollection(Collection<Userreport> userreportCollection) {
        this.userreportCollection = userreportCollection;
    }

    @XmlTransient
    public Collection<Userfood> getUserfoodCollection() {
        return userfoodCollection;
    }

    public void setUserfoodCollection(Collection<Userfood> userfoodCollection) {
        this.userfoodCollection = userfoodCollection;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.health.User[ id=" + id + " ]";
    }
    
}
