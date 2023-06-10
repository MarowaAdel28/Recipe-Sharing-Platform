package gov.iti.jets.repositories;

import gov.iti.jets.models.entities.RecipeHasIngredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RecipeHasIngredientsRepository extends JpaRepository<RecipeHasIngredients, Integer>, JpaSpecificationExecutor<RecipeHasIngredients> {

}