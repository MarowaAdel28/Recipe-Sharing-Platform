/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.iti.jets.models.entities;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 *
 * @author marwa
 */
@Entity
@Table(name = "rejected_recipe")
@NamedQueries({
    @NamedQuery(name = "RejectedRecipe.findAll", query = "SELECT r FROM RejectedRecipe r")})
public class RejectedRecipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "message")
    private String message;
    @Id
    @Basic(optional = false)
    @Column(name = "recipe_id")
    private Integer recipeId;
    @JoinColumn(name = "recipe_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Recipe recipe;

    public RejectedRecipe() {
    }

    public RejectedRecipe(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public RejectedRecipe(Integer recipeId, String message) {
        this.recipeId = recipeId;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recipeId != null ? recipeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RejectedRecipe)) {
            return false;
        }
        RejectedRecipe other = (RejectedRecipe) object;
        if ((this.recipeId == null && other.recipeId != null) || (this.recipeId != null && !this.recipeId.equals(other.recipeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gov.iti.jets.entities.RejectedRecipe[ recipeId=" + recipeId + " ]";
    }
    
}
