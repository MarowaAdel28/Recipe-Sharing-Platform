package gov.iti.jets.repositories;

import gov.iti.jets.models.dtos.RateDto;
import gov.iti.jets.models.entities.Rate;
import gov.iti.jets.models.entities.RatePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, RatePK> {

    public List<Rate> findByRecipeId (Integer recipeId);
}
