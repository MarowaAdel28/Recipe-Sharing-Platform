/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.iti.jets.models.entities;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 *
 * @author marwa
 */
@Embeddable
public class FavoriteRecipePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "recipe_id")
    private int recipeId;

    public FavoriteRecipePK() {
    }

    public FavoriteRecipePK(int id, int userId, int recipeId) {
        this.id = id;
        this.userId = userId;
        this.recipeId = recipeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        hash += (int) id;
        hash += (int) userId;
        hash += (int) recipeId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FavoriteRecipePK)) {
            return false;
        }
        FavoriteRecipePK other = (FavoriteRecipePK) object;
        if (this.id != other.id) {
            return false;
        }
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
        return "gov.iti.jets.entities.FavoriteRecipePK[ id=" + id + ", userId=" + userId + ", recipeId=" + recipeId + " ]";
    }
    
}
