/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.iti.jets.models.entities;

import java.io.Serializable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 *
 * @author marwa
 */
@Entity
@Table(name = "favorite_recipe")
@NamedQueries({
    @NamedQuery(name = "FavoriteRecipe.findAll", query = "SELECT f FROM FavoriteRecipe f")})
public class FavoriteRecipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FavoriteRecipePK favoriteRecipePK;
    @JoinColumn(name = "recipe_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recipe recipe;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public FavoriteRecipe() {
    }

    public FavoriteRecipe(FavoriteRecipePK favoriteRecipePK) {
        this.favoriteRecipePK = favoriteRecipePK;
    }

    public FavoriteRecipe(int id, int userId, int recipeId) {
        this.favoriteRecipePK = new FavoriteRecipePK(id, userId, recipeId);
    }

    public FavoriteRecipePK getFavoriteRecipePK() {
        return favoriteRecipePK;
    }

    public void setFavoriteRecipePK(FavoriteRecipePK favoriteRecipePK) {
        this.favoriteRecipePK = favoriteRecipePK;
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
        hash += (favoriteRecipePK != null ? favoriteRecipePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FavoriteRecipe)) {
            return false;
        }
        FavoriteRecipe other = (FavoriteRecipe) object;
        if ((this.favoriteRecipePK == null && other.favoriteRecipePK != null) || (this.favoriteRecipePK != null && !this.favoriteRecipePK.equals(other.favoriteRecipePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gov.iti.jets.entities.FavoriteRecipe[ favoriteRecipePK=" + favoriteRecipePK + " ]";
    }
    
}
