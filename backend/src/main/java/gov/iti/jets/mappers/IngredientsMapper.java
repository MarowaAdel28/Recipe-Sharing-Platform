package gov.iti.jets.mappers;

import gov.iti.jets.models.dtos.IngredientsDTO;
import gov.iti.jets.models.dtos.recipeposter.RecipeIngredientsDTO;
import gov.iti.jets.models.entities.Ingredients;
import org.springframework.stereotype.Component;

@Component
public class IngredientsMapper {

    public RecipeIngredientsDTO toDto(Ingredients ingredients) {
        return new RecipeIngredientsDTO()
                .builder()
                .id(ingredients.getId())
                .ingredientName(ingredients.getName())
                .build();
    }


}
