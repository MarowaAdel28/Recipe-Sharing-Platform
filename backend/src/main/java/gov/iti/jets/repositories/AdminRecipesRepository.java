package gov.iti.jets.repositories;

import gov.iti.jets.models.dtos.adminrecipes.AdminRecipeDTO;
import gov.iti.jets.models.entities.Recipe;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AdminRecipesRepository extends JpaRepository<Recipe, Integer> {
    @Transactional
    @Modifying
    @Query("update Recipe r set r.status = ?2 where r.id = ?1")
    int updateStatusById( Integer id, String status);
    long countDistinctByStatusIgnoreCase(String status);
    List<AdminRecipeDTO> findByStatusIgnoreCase(String status, PageRequest pageRequest);
}