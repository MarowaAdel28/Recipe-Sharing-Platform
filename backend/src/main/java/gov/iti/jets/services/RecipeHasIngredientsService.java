package gov.iti.jets.services;

import gov.iti.jets.models.dtos.recipeposter.RecipeIngredientsDTO;
import gov.iti.jets.models.entities.RecipeHasIngredients;
import gov.iti.jets.models.entities.RecipeHasIngredientsPK;
import gov.iti.jets.repositories.RecipeHasIngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeHasIngredientsService {

    @Autowired
    private RecipeHasIngredientsRepository recipeHasIngredientsRepository;

    public void save(RecipeIngredientsDTO recipeIngredientsDTO, Integer recipeId) {
        RecipeHasIngredientsPK recipeHasIngredientsPK = new RecipeHasIngredientsPK();
        recipeHasIngredientsPK.setRecipeId(recipeId);
        recipeHasIngredientsPK.setIngredientsId(recipeIngredientsDTO.getId());

        RecipeHasIngredients recipeHasIngredients = new RecipeHasIngredients();
        recipeHasIngredients.setRecipeHasIngredientsPK(recipeHasIngredientsPK);
        recipeHasIngredients.setQuantity(recipeIngredientsDTO.getIngredientQuantity());
        recipeHasIngredientsRepository.save(recipeHasIngredients);
    }

    public void addListOfRecipeIngredients(List<RecipeIngredientsDTO> recipeIngredientsDTOList, Integer recipeId) {
        for(RecipeIngredientsDTO recipeIngredientsDTO: recipeIngredientsDTOList) {
            save(recipeIngredientsDTO, recipeId);
        }
    }



}
