package gov.iti.jets.services;

import gov.iti.jets.models.dtos.RecipeDTO;
import gov.iti.jets.models.entities.Recipe;
import gov.iti.jets.repositories.RecipeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Integer save(RecipeDTO recipeDto) {
        Recipe recipe = new Recipe();
        BeanUtils.copyProperties(recipeDto, recipe);
        recipe = recipeRepository.save(recipe);
        return recipe.getId();
    }

    public void delete(Integer id) {
        recipeRepository.deleteById(id);
    }

    public void update(RecipeDTO recipeDto) {
        Recipe bean = requireOne(recipeDto.getId());
        BeanUtils.copyProperties(recipeDto, bean);
        recipeRepository.save(bean);
    }

    public RecipeDTO getById(Integer id) {
        Recipe original = requireOne(id);
        return toDTO(original);
    }


    private RecipeDTO toDTO(Recipe recipe) {
        RecipeDTO recipeDto = new RecipeDTO();
        BeanUtils.copyProperties(recipe, recipeDto);
        return recipeDto;
    }

    private Recipe requireOne(Integer id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
