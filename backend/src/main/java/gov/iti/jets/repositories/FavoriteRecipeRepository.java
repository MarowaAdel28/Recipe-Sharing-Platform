package gov.iti.jets.repositories;

import gov.iti.jets.models.entities.FavoriteRecipe;
import gov.iti.jets.models.entities.Recipe;
import gov.iti.jets.models.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRecipeRepository extends JpaRepository<FavoriteRecipe, Integer> {
    @Modifying
    @Transactional
    void deleteFavoriteRecipeByRecipeIdAndUserId(Recipe recipeId, User userId);

    Optional<FavoriteRecipe> findByRecipeIdAndUserId(Recipe recipeId, User userId);

    FavoriteRecipe findDistinctByRecipeIdAndUserId(Recipe recipeId, User userId);

    Page<FavoriteRecipe> findRecipeIdByUserIdAndRecipeIdIsDeletedFalse(User user, Pageable pageable);
    List<FavoriteRecipe> findRecipeIdByUserIdAndRecipeIdIsDeletedFalse(User user);
}