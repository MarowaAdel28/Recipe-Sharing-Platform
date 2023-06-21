package gov.iti.jets.services;

import gov.iti.jets.mappers.RecipeMapper;
import gov.iti.jets.models.dtos.recipeposter.RecipeIngredientsDTO;
import gov.iti.jets.models.dtos.recipeposter.RecipeSetterDTO;
import gov.iti.jets.models.entities.*;
import gov.iti.jets.repositories.CategoryRepository;
import gov.iti.jets.repositories.RecipeRepository;
import gov.iti.jets.repositories.UserRepository;
import gov.iti.jets.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private RecipeMapper recipeMapper;

    @Autowired
    private IngredientsService ingredientsService;

    @Autowired
    private RecipeHasIngredientsService recipeHasIngredientsService;

    public Integer save(RecipeSetterDTO recipeDto) {
//        System.out.println("recipeDto.getCategoryId() = " + recipeDto.getCategoryId());
        Category category = categoryService.getReference(recipeDto.getCategoryId());
        User user = userService.getReference(recipeDto.getUserId());
        if (user == null) {
            return Utility.INVALID_USER;
        }
        if (category == null) {
            return Utility.INVALID_CATEGORY;
        }
        Recipe recipe = recipeMapper.toEntity(recipeDto);
        recipe.setUserId(user);
        recipe.setCategoryId(category);
        recipe = recipeRepository.save(recipe);

        List<RecipeIngredientsDTO> ingredients = ingredientsService.addListOfIngredients(recipeDto.getIngredients());
        recipeHasIngredientsService.addListOfRecipeIngredients(ingredients, recipe.getId());
        return recipe.getId();
    }

    public void delete(Integer id) {
        recipeRepository.deleteById(id);
    }

//    public void update(RecipeDTO recipeDto) {
//        Recipe bean = requireOne(recipeDto.getId());
//        BeanUtils.copyProperties(recipeDto, bean);
//        recipeRepository.save(bean);
//    }

//    public RecipeDTO getById(Integer id) {
//        Recipe original = requireOne(id);
//        return toDTO(original);
//    }


//    private RecipeDTO toDTO(Recipe recipeposter) {
//        RecipeDTO recipeDto = new RecipeDTO();
//        BeanUtils.copyProperties(recipeposter, recipeDto);
//        return recipeDto;
//    }

    private Recipe requireOne(Integer id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
