package gov.iti.jets.models.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.iti.jets.annotations.CategoryValidator;
import gov.iti.jets.models.dtos.request.RecipeIngredientsDTO;
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

    Integer id;

    @NotNull
    private String recipeName;

    @NotNull
    private List<String> steps;
    @NotNull
    private List<RecipeIngredientsDTO> ingredients;
    @Size(min=1, max=15)
    private String preparingTime;

    @NotNull
    private Integer numberOfPersons;

    @NotNull
    @JsonProperty("user")
    private Integer userId;

    @NotNull
    @CategoryValidator
    @JsonProperty("category")
    private Integer categoryId;



//    private List<ImageDTO> imageList;


}
