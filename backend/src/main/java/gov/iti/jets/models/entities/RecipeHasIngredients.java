/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gov.iti.jets.models.entities;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "recipe_has_ingredients")
public class RecipeHasIngredients implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RecipeHasIngredientsPK recipeHasIngredientsPK;
    @Basic(optional = false)
    @Column(name = "quantity")
    private String quantity;
    @JoinColumn(name = "ingredients_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ingredients ingredients;
    @JoinColumn(name = "recipe_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recipe recipe;

    public RecipeHasIngredients() {
    }

    public RecipeHasIngredients(RecipeHasIngredientsPK recipeHasIngredientsPK) {
        this.recipeHasIngredientsPK = recipeHasIngredientsPK;
    }

    public RecipeHasIngredients(RecipeHasIngredientsPK recipeHasIngredientsPK, String quantity) {
        this.recipeHasIngredientsPK = recipeHasIngredientsPK;
        this.quantity = quantity;
    }

    public RecipeHasIngredients(int recipeId, int ingredientsId) {
        this.recipeHasIngredientsPK = new RecipeHasIngredientsPK(recipeId, ingredientsId);
    }

    public RecipeHasIngredientsPK getRecipeHasIngredientsPK() {
        return recipeHasIngredientsPK;
    }

    public void setRecipeHasIngredientsPK(RecipeHasIngredientsPK recipeHasIngredientsPK) {
        this.recipeHasIngredientsPK = recipeHasIngredientsPK;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
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
        hash += (recipeHasIngredientsPK != null ? recipeHasIngredientsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecipeHasIngredients)) {
            return false;
        }
        RecipeHasIngredients other = (RecipeHasIngredients) object;
        if ((this.recipeHasIngredientsPK == null && other.recipeHasIngredientsPK != null) || (this.recipeHasIngredientsPK != null && !this.recipeHasIngredientsPK.equals(other.recipeHasIngredientsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gov.iti.jets.models.entities.RecipeHasIngredients[ recipeHasIngredientsPK=" + recipeHasIngredientsPK + " ]";
    }
    
}
