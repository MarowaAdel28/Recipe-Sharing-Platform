package gov.iti.jets.models.dtos;


import gov.iti.jets.models.entities.Recipe;
import gov.iti.jets.models.entities.User;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ReviewDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String comment;

    private LocalDateTime date;

//    private RecipeDTO recipeId;
  
    private UserDTO userId;

}
