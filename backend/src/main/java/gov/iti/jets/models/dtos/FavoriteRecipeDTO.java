package gov.iti.jets.models.dtos;


import gov.iti.jets.models.entities.Recipe;
import gov.iti.jets.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
public class FavoriteRecipeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private RecipeDTO recipeId;
   
    private UserDTO userId;

}
