package gov.iti.jets.services;

import gov.iti.jets.models.dtos.RecipeDTO;
import gov.iti.jets.models.dtos.RecipeResponseDTO;
import gov.iti.jets.models.entities.Recipe;
import gov.iti.jets.repositories.RecipeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private UserService userService;

    @Autowired
    private RecipeMapper recipeMapper;

    @Autowired
    private IngredientsService ingredientsService;

    @Autowired
    private RecipeHasIngredientsService recipeHasIngredientsService;

    public Integer save(RecipeSetterDTO recipeDto) {
        System.out.println("recipeDto.getCategoryId() = " + recipeDto.getCategoryId());
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

    public void update(RecipeDTO recipeDto) {
        Recipe bean = requireOne(recipeDto.getId());
        recipeRepository.save(bean);
    }

    public RecipeDTO getById(Integer id) {
        Recipe original = requireOne(id);
        System.out.println("original = " + original);
        System.out.println("toDTO(original).getDate() = " + toDTO(original));
        return toDTO(original);
    }


    private RecipeDTO toDTO(Recipe recipe) {
        return modelMapper.map(recipe,RecipeDTO.class);
    }

    private Recipe toEntity(RecipeDTO recipeDto) {
        return modelMapper.map(recipeDto,Recipe.class);
    }

    private Recipe requireOne(Integer id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<RecipeDTO> getTop3() {
        Pageable pageable = PageRequest.of(0, 3); // Limit the results to 3
        List<Recipe> categories = recipeRepository.findAll(pageable).getContent();
        List<RecipeDTO> recipeDTOS = new ArrayList<>();
        for (Recipe recipe : categories)
            recipeDTOS.add(toDTO(recipe));
        return recipeDTOS;
    }

    public List<RecipeDTO> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //    public List<RecipeDTO> getPaginatedRecipes(int page, int pageSize) {
//        Pageable pageable = PageRequest.of(page, pageSize);
//        return recipeRepository.findAll(pageable).stream()
//                .map(this::toDTO)
//                .collect(Collectors.toList());
//    }
    public ResponseEntity<RecipeResponseDTO> getPaginatedRecipes(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        RecipeResponseDTO recipeResponseDTO = new RecipeResponseDTO();
        recipeResponseDTO.setData(recipeRepository.findAll(pageable).stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
        recipeResponseDTO.setTotalItems(getRecipesCount());
        return ResponseEntity.ok(recipeResponseDTO);
    }
    private int getRecipesCount(){
        return (int)recipeRepository.count();
    }
}
