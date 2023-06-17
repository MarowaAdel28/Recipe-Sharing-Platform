package gov.iti.jets.models.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class RecipeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer cooksCount;

    private String steps;

    private String recipeName;

    private String status;

    private LocalDateTime date;

    private Boolean deleted;

    private String preparingTime;

    private Integer persons;

    private List<ImageDTO> imageList;

    private List<ReviewDTO> reviewList;

    private CategoryDTO category;

    private UserDTO userId;

    private List<RecipeHasIngredientsDTO> recipeHasIngredientsList;



}
