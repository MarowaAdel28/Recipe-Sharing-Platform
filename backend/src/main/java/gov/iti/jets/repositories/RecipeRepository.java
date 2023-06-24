package gov.iti.jets.repositories;

import gov.iti.jets.models.dtos.stats.RecipeStatusStatDTO;
import gov.iti.jets.models.entities.Category;
import gov.iti.jets.models.entities.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import gov.iti.jets.models.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import java.util.List;

@Repository
@EnableJpaRepositories
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

    Page<Recipe> findAllByUserIdAndIsDeletedFalse(User userId, Pageable pageable);
    List<Recipe> findAllByUserIdAndIsDeletedFalse(User userId);

    Page<Recipe> findAllByUserIdAndIsDeletedTrue(User userId, Pageable pageable);
    List<Recipe> findAllByUserIdAndIsDeletedTrue(User userId);

    @Transactional
    @Modifying
    @Query("update Recipe r set r.isDeleted = :isDeleted where r.id = :id ")
    void updateRecipeDeletion(
            @Param("isDeleted") boolean isDeleted ,
            @Param("id") int id);

    @Query(nativeQuery = true)
    RecipeStatusStatDTO findStatusCount();

}