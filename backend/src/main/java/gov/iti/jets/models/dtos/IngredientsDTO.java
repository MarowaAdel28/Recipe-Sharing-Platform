package gov.iti.jets.models.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String name;
    
//    private List<RecipeHasIngredientsDTO> recipeHasIngredientsList;


}
