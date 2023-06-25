package gov.iti.jets.models.dtos.adminrecipes;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RejectedRecipeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer recipeId;
    
    private String message;

//    private RecipeDTO recipe;

}
