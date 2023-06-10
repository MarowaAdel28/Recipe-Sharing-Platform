package gov.iti.jets.repositories;

import gov.iti.jets.models.entities.RejectedRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RejectedRecipeRepository extends JpaRepository<RejectedRecipe, Integer> {

}