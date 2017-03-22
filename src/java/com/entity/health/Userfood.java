/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity.health;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "userfood")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userfood.findByFoodNameAndDate", query = "SELECT u FROM Userfood u JOIN Food f ON u.userfoodPK.foodId=f.id WHERE f.name=:foodName AND u.userfoodPK.date=:date"),
    @NamedQuery(name = "Userfood.findAll", query = "SELECT u FROM Userfood u"),
    @NamedQuery(name = "Userfood.findByUserId", query = "SELECT u FROM Userfood u WHERE u.userfoodPK.userId = :userId"),
    @NamedQuery(name = "Userfood.findByFoodId", query = "SELECT u FROM Userfood u WHERE u.userfoodPK.foodId = :foodId"),
    @NamedQuery(name = "Userfood.findByDate", query = "SELECT u FROM Userfood u WHERE u.userfoodPK.date = :date"),
    @NamedQuery(name = "Userfood.findByTime", query = "SELECT u FROM Userfood u WHERE u.time = :time"),
    @NamedQuery(name = "Userfood.findByAmount", query = "SELECT u FROM Userfood u WHERE u.amount = :amount")})
public class Userfood implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserfoodPK userfoodPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Time")
    @Temporal(TemporalType.TIME)
    private Date time;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Amount")
    private double amount;
    @JoinColumn(name = "FoodId", referencedColumnName = "Id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Food food;
    @JoinColumn(name = "UserId", referencedColumnName = "Id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Userfood() {
    }

    public Userfood(UserfoodPK userfoodPK) {
        this.userfoodPK = userfoodPK;
    }

    public Userfood(UserfoodPK userfoodPK, Date time, double amount) {
        this.userfoodPK = userfoodPK;
        this.time = time;
        this.amount = amount;
    }

    public Userfood(int userId, int foodId, Date date) {
        this.userfoodPK = new UserfoodPK(userId, foodId, date);
    }

    public UserfoodPK getUserfoodPK() {
        return userfoodPK;
    }

    public void setUserfoodPK(UserfoodPK userfoodPK) {
        this.userfoodPK = userfoodPK;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userfoodPK != null ? userfoodPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userfood)) {
            return false;
        }
        Userfood other = (Userfood) object;
        if ((this.userfoodPK == null && other.userfoodPK != null) || (this.userfoodPK != null && !this.userfoodPK.equals(other.userfoodPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.health.Userfood[ userfoodPK=" + userfoodPK + " ]";
    }
    
}
