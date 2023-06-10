package gov.iti.jets.repositories;

import gov.iti.jets.models.entities.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IngredientsRepository extends JpaRepository<Ingredients, Integer>, JpaSpecificationExecutor<Ingredients> {

}