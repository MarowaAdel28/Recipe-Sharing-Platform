/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.iti.jets.models.entities;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 *
 * @author marwa
 */
@Entity
@Table(name = "rate")
@NamedQueries({
    @NamedQuery(name = "Rate.findAll", query = "SELECT r FROM Rate r")
    , @NamedQuery(name = "Rate.findByUserId", query = "SELECT r FROM Rate r WHERE r.ratePK.userId = :userId")
    , @NamedQuery(name = "Rate.findByRecipeId", query = "SELECT r FROM Rate r WHERE r.ratePK.recipeId = :recipeId")
    , @NamedQuery(name = "Rate.findByRate", query = "SELECT r FROM Rate r WHERE r.rate = :rate")})
public class Rate implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RatePK ratePK;
    @Basic(optional = false)
    @Column(name = "rate")
    private int rate;
    @JoinColumn(name = "recipe_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recipe recipe;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Rate() {
    }

    public Rate(RatePK ratePK) {
        this.ratePK = ratePK;
    }

    public Rate(RatePK ratePK, int rate) {
        this.ratePK = ratePK;
        this.rate = rate;
    }

    public Rate(int userId, int recipeId) {
        this.ratePK = new RatePK(userId, recipeId);
    }

    public RatePK getRatePK() {
        return ratePK;
    }

    public void setRatePK(RatePK ratePK) {
        this.ratePK = ratePK;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
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
        hash += (ratePK != null ? ratePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rate)) {
            return false;
        }
        Rate other = (Rate) object;
        if ((this.ratePK == null && other.ratePK != null) || (this.ratePK != null && !this.ratePK.equals(other.ratePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication2.Rate[ ratePK=" + ratePK + " ]";
    }
    
}
