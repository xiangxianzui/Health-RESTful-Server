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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Administrator
 */
@Embeddable
public class UserfoodPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "UserId")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FoodId")
    private int foodId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public UserfoodPK() {
    }

    public UserfoodPK(int userId, int foodId, Date date) {
        this.userId = userId;
        this.foodId = foodId;
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) foodId;
        hash += (date != null ? date.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserfoodPK)) {
            return false;
        }
        UserfoodPK other = (UserfoodPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.foodId != other.foodId) {
            return false;
        }
        if ((this.date == null && other.date != null) || (this.date != null && !this.date.equals(other.date))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.health.UserfoodPK[ userId=" + userId + ", foodId=" + foodId + ", date=" + date + " ]";
    }
    
}
