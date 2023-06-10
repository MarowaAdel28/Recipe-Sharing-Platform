package gov.iti.jets.models.dtos;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String name;

    private Boolean deleted;
    
    private List<RecipeDTO> recipeList;


}
