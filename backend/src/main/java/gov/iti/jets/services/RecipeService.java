package gov.iti.jets.services;

import gov.iti.jets.models.dtos.RecipeDTO;
import gov.iti.jets.models.dtos.RecipeResponseDTO;
import gov.iti.jets.models.entities.Recipe;
import gov.iti.jets.repositories.RecipeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
