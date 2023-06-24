package gov.iti.jets.models.dtos.response;

import gov.iti.jets.models.dtos.RecipeDTO;
import gov.iti.jets.models.dtos.UserDTO;
import lombok.Data;

import java.io.Serializable;
@Data
public class FavouriteResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private RecipeDTO recipeId;

    private UserDTO userId;
}
