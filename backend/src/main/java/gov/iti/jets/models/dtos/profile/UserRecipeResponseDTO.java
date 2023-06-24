package gov.iti.jets.models.dtos.profile;


import gov.iti.jets.models.dtos.RecipeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRecipeResponseDTO {

    private List<UserRecipeDTO> data;
    private long totalItems;


}