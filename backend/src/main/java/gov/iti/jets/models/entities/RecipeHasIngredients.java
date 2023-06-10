package gov.iti.jets.models.entities;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "recipe_has_ingredients")
public class RecipeHasIngredients implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "recipe_id", nullable = false)
    private Integer recipeId;

    @Id
    @Column(name = "ingredients_id", nullable = false)
    private Integer ingredientsId;

    @Column(name = "quantity", nullable = false)
    private String quantity;

}
