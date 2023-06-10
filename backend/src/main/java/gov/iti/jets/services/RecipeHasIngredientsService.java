package gov.iti.jets.services;

import gov.iti.jets.models.dtos.RecipeHasIngredientsDTO;
import gov.iti.jets.models.entities.RecipeHasIngredients;
import gov.iti.jets.repositories.RecipeHasIngredientsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RecipeHasIngredientsService {

    @Autowired
    private RecipeHasIngredientsRepository recipeHasIngredientsRepository;

    public Integer save(RecipeHasIngredientsDTO recipeHasIngredientsDto) {
        RecipeHasIngredients bean = new RecipeHasIngredients();
        BeanUtils.copyProperties(recipeHasIngredientsDto, bean);
        bean = recipeHasIngredientsRepository.save(bean);
        return bean.getRecipeId();
    }

    public void delete(Integer id) {
        recipeHasIngredientsRepository.deleteById(id);
    }

    public void update(Integer id, RecipeHasIngredientsDTO recipeHasIngredientsDto) {
        RecipeHasIngredients bean = requireOne(id);
        BeanUtils.copyProperties(recipeHasIngredientsDto, bean);
        recipeHasIngredientsRepository.save(bean);
    }

    public RecipeHasIngredientsDTO getById(Integer id) {
        RecipeHasIngredients original = requireOne(id);
        return toDTO(original);
    }


    private RecipeHasIngredientsDTO toDTO(RecipeHasIngredients original) {
        RecipeHasIngredientsDTO bean = new RecipeHasIngredientsDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private RecipeHasIngredients requireOne(Integer id) {
        return recipeHasIngredientsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
