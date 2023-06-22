package gov.iti.jets.models.dtos.adminrecipes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.iti.jets.models.entities.Ingredients;
import gov.iti.jets.models.entities.RecipeHasIngredients;
import gov.iti.jets.models.entities.RejectedRecipe;
import gov.iti.jets.models.entities.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


public interface AdminRecipeDTO extends Serializable {
    Integer getId();
    @JsonProperty("user")
    User getUserId();
    String getName();
    String getSteps();
    @JsonFormat(pattern="dd/MM/yyyy hh:mm")
    LocalDateTime getDate();
    String getPreparingTime();
    Integer getPersons();
    Integer getCooksCount();

    RejectedRecipe getRejectedRecipe();

    @JsonProperty("ingredientsList")
    List<RecipeHasIngredients> getRecipeHasIngredientsList();

    interface RecipeHasIngredients{
        String getQuantity();
        Ingredients getIngredients();

        interface Ingredients{
            String getName();
        }
    }

    interface User {
        String getUsername();
    }

    interface RejectedRecipe {
        String getMessage();
    }

}
