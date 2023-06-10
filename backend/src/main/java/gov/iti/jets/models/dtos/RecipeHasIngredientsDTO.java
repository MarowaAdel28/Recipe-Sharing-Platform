package gov.iti.jets.models.dtos;


import lombok.Data;

import java.io.Serializable;

@Data
public class RecipeHasIngredientsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer recipeId;

    private Integer ingredientsId;

    private String quantity;

}
