package gov.iti.jets.repositories;

import gov.iti.jets.models.dtos.stats.RecipeStatusStatDTO;
import gov.iti.jets.models.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @Query(nativeQuery = true)
    RecipeStatusStatDTO findStatusCount();

}