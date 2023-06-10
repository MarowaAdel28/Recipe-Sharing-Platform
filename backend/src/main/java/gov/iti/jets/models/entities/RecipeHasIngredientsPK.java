/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gov.iti.jets.models.entities;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 *
 * @author dell
 */
@Embeddable
public class RecipeHasIngredientsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "recipe_id")
    private int recipeId;
    @Basic(optional = false)
    @Column(name = "ingredients_id")
    private int ingredientsId;

    public RecipeHasIngredientsPK() {
    }

    public RecipeHasIngredientsPK(int recipeId, int ingredientsId) {
        this.recipeId = recipeId;
        this.ingredientsId = ingredientsId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getIngredientsId() {
        return ingredientsId;
    }

    public void setIngredientsId(int ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) recipeId;
        hash += (int) ingredientsId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecipeHasIngredientsPK)) {
            return false;
        }
        RecipeHasIngredientsPK other = (RecipeHasIngredientsPK) object;
        if (this.recipeId != other.recipeId) {
            return false;
        }
        if (this.ingredientsId != other.ingredientsId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gov.iti.jets.models.entities.RecipeHasIngredientsPK[ recipeId=" + recipeId + ", ingredientsId=" + ingredientsId + " ]";
    }
    
}
