package gov.iti.jets.models.dtos;


import lombok.Data;

import java.io.Serializable;

@Data
public class RejectedRecipeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String message;

    private Integer recipeId;

}
