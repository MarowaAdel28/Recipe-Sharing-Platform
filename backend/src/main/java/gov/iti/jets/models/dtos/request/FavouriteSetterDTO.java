package gov.iti.jets.models.dtos.request;

import gov.iti.jets.models.dtos.RecipeDTO;
import gov.iti.jets.models.dtos.UserDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class FavouriteSetterDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer recipeId;

    private Integer userId;
}
