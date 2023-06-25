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
@Embeddable
public class RatePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "recipe_id")
    private int recipeId;

    public RatePK() {
    }

    public RatePK(int userId, int recipeId) {
        this.userId = userId;
        this.recipeId = recipeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) recipeId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RatePK)) {
            return false;
        }
        RatePK other = (RatePK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.recipeId != other.recipeId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RatePK[ userId=" + userId + ", recipeId=" + recipeId + " ]";
    }
    
}
