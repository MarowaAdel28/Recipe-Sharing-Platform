package gov.iti.jets.repositories;

import gov.iti.jets.models.entities.FavoriteRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FavoriteRecipeRepository extends JpaRepository<FavoriteRecipe, Integer>, JpaSpecificationExecutor<FavoriteRecipe> {

}