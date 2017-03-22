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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "userreport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userreport.findAll", query = "SELECT u FROM Userreport u"),
    @NamedQuery(name = "Userreport.findByUserId", query = "SELECT u FROM Userreport u WHERE u.userreportPK.userId = :userId"),
    @NamedQuery(name = "Userreport.findByDate", query = "SELECT u FROM Userreport u WHERE u.userreportPK.date = :date"),
    @NamedQuery(name = "Userreport.findByCalConsumed", query = "SELECT u FROM Userreport u WHERE u.calConsumed = :calConsumed"),
    @NamedQuery(name = "Userreport.findByCalBurned", query = "SELECT u FROM Userreport u WHERE u.calBurned = :calBurned"),
    @NamedQuery(name = "Userreport.findBySteps", query = "SELECT u FROM Userreport u WHERE u.steps = :steps"),
    @NamedQuery(name = "Userreport.findByCalorieGoal", query = "SELECT u FROM Userreport u WHERE u.calorieGoal = :calorieGoal"),
    @NamedQuery(name = "Userreport.findByRemaining", query = "SELECT u FROM Userreport u WHERE u.remaining = :remaining")})
public class Userreport implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserreportPK userreportPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CalConsumed")
    private double calConsumed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CalBurned")
    private double calBurned;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Steps")
    private double steps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CalorieGoal")
    private double calorieGoal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Remaining")
    private double remaining;
    @JoinColumn(name = "UserId", referencedColumnName = "Id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Userreport() {
    }

    public Userreport(UserreportPK userreportPK) {
        this.userreportPK = userreportPK;
    }

    public Userreport(UserreportPK userreportPK, double calConsumed, double calBurned, double steps, double calorieGoal, double remaining) {
        this.userreportPK = userreportPK;
        this.calConsumed = calConsumed;
        this.calBurned = calBurned;
        this.steps = steps;
        this.calorieGoal = calorieGoal;
        this.remaining = remaining;
    }

    public Userreport(int userId, Date date) {
        this.userreportPK = new UserreportPK(userId, date);
    }

    public UserreportPK getUserreportPK() {
        return userreportPK;
    }

    public void setUserreportPK(UserreportPK userreportPK) {
        this.userreportPK = userreportPK;
    }

    public double getCalConsumed() {
        return calConsumed;
    }

    public void setCalConsumed(double calConsumed) {
        this.calConsumed = calConsumed;
    }

    public double getCalBurned() {
        return calBurned;
    }

    public void setCalBurned(double calBurned) {
        this.calBurned = calBurned;
    }

    public double getSteps() {
        return steps;
    }

    public void setSteps(double steps) {
        this.steps = steps;
    }

    public double getCalorieGoal() {
        return calorieGoal;
    }

    public void setCalorieGoal(double calorieGoal) {
        this.calorieGoal = calorieGoal;
    }

    public double getRemaining() {
        return remaining;
    }

    public void setRemaining(double remaining) {
        this.remaining = remaining;
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
        hash += (userreportPK != null ? userreportPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userreport)) {
            return false;
        }
        Userreport other = (Userreport) object;
        if ((this.userreportPK == null && other.userreportPK != null) || (this.userreportPK != null && !this.userreportPK.equals(other.userreportPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.health.Userreport[ userreportPK=" + userreportPK + " ]";
    }
    
}
