package gov.iti.jets.models.dtos.recipeposter;

import gov.iti.jets.annotations.CategoryValidator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeSetterDTO implements Serializable {

    @NotNull
    private List<String> steps;
    @NotNull
    private List<RecipeIngredientsDTO> ingredients;
    @Size(min=1, max=15)
    private String preparingTime;

    @NotNull
    private Integer numberOfPersons;

    @NotNull
    private Integer userId;

    @NotNull
    @CategoryValidator
    private Integer categoryId;



//    private List<ImageDTO> imageList;


}
