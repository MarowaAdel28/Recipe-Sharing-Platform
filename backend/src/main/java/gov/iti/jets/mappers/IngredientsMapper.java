package gov.iti.jets.mappers;

import gov.iti.jets.models.dtos.request.RecipeIngredientsDTO;
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
