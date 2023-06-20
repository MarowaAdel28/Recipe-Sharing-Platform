package gov.iti.jets.repositories;

import gov.iti.jets.models.entities.RecipeHasIngredients;
import gov.iti.jets.models.entities.RecipeHasIngredientsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeHasIngredientsRepository extends JpaRepository<RecipeHasIngredients, RecipeHasIngredientsPK> {
}
