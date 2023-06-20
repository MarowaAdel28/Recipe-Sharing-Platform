package gov.iti.jets.mappers;

import gov.iti.jets.models.dtos.recipeposter.RecipeSetterDTO;
import gov.iti.jets.models.entities.Recipe;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RecipeMapper {

    public RecipeSetterDTO toDto(Recipe recipe){
        return new RecipeSetterDTO();
    }


    public Recipe toEntity(RecipeSetterDTO recipeDto){
        Recipe recipe = new Recipe();
        recipe.setSteps(recipeDto.getSteps().toString());
        recipe.setDate(new Date());
        recipe.setPersons(recipeDto.getNumberOfPersons());
        recipe.setPreparingTime(recipeDto.getPreparingTime());
        recipe.setRecipeName(recipeDto.getRecipeName());
        return recipe;
    }
}
