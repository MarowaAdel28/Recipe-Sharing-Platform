package gov.iti.jets.models.dtos.recipeposter;

import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeIngredientsDTO implements Serializable {

    private Integer id;
    private String ingredientName;
    private String ingredientQuantity;

}
