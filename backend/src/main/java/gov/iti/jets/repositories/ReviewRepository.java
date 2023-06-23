package gov.iti.jets.repositories;

import gov.iti.jets.models.dtos.request.RecipeSetterDTO;
import gov.iti.jets.models.entities.Recipe;
import gov.iti.jets.models.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query(value = "select r from Review r where r.recipeId = :recipeId order by current date DESC ")
    List<Review> retreiveReviewsByRecipeId(@Param("recipeId") Recipe recipeId);

    List<Review> findReviewByRecipeIdOrderByDateDesc(Recipe recipeId);
}