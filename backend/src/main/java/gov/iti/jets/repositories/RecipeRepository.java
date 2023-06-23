package gov.iti.jets.repositories;

import gov.iti.jets.models.entities.Category;
import gov.iti.jets.models.entities.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    //    @Query("SELECT r FROM Recipe r WHERE LOWER(r.recipeName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
//    List<Recipe> findByNameContainingIgnoreCase(@Param("keyword") String keyword);
//    @Query("SELECT r FROM Recipe r WHERE LOWER(r.recipeName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
//    Page<Recipe> searchByNameContainingIgnoreCase(
//            @Param("keyword") String keyword,
//            Pageable pageable
//    );

    @Query("SELECT r FROM Recipe r WHERE (:name is null or LOWER(r.recipeName) LIKE LOWER(concat('%', :name, '%'))) " +
            "AND (:categoryId is null or r.categoryId = :categoryId)")
    List<Recipe> searchByNameAndCategoryIgnoreCase(@Param("name") String name, @Param("categoryId") Category category);

}