package gov.iti.jets.models.dtos;


import gov.iti.jets.models.entities.Recipe;
import lombok.Data;

import java.io.Serializable;

@Data
public class RejectedRecipeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    
    private String message;

    private RecipeDTO recipe;

}
