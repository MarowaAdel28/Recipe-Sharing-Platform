package gov.iti.jets.models.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String name;

    private Integer cooksCount;

    private String steps;

    private String status;

    private Date date;

    private Boolean deleted;

    private String preparingTime;

    private Integer persons;

    private List<ImageDTO> imageList;

    @JsonIgnore
    private List<FavoriteRecipeDTO> favoriteRecipeList;

    private List<ReviewDTO> reviewList;

    private CategoryDTO categoryId;

    private UserDTO userId;

    private List<RecipeHasIngredientsDTO> recipeHasIngredientsList;



}
