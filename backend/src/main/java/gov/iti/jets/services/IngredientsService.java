package gov.iti.jets.services;

import gov.iti.jets.mappers.IngredientsMapper;
import gov.iti.jets.models.dtos.IngredientsDTO;
import gov.iti.jets.models.dtos.recipeposter.RecipeIngredientsDTO;
import gov.iti.jets.models.entities.Ingredients;
import gov.iti.jets.repositories.IngredientsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IngredientsService {

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Autowired
    private IngredientsMapper ingredientsMapper;
    private RecipeIngredientsDTO ing;

    public Integer save(String ingredient) {
        Ingredients ingredients = new Ingredients();
        ingredients.setName(ingredient);
        ingredients = ingredientsRepository.save(ingredients);
        return ingredients.getId();
    }


    public RecipeIngredientsDTO getByName(String name) {
        Ingredients ingredients = ingredientsRepository.findByNameIgnoreCase(name);
        if(ingredients != null)
            return ingredientsMapper.toDto(ingredients);
        return  null;
    }


    public List<RecipeIngredientsDTO> addListOfIngredients(List<RecipeIngredientsDTO> ingredients) {
        for(RecipeIngredientsDTO ingredient: ingredients) {
            RecipeIngredientsDTO ing = getByName(ingredient.getIngredientName());
            if(ing == null) {
                ingredient.setId(save(ingredient.getIngredientName()));
            }
            else {
                ingredient.setId(ing.getId());
            }
        }
        return ingredients;
    }


//    public void delete(Integer id) {
//        ingredientsRepository.deleteById(id);
//    }

//    public void update( IngredientsDTO ingredientsDto) {
//        Ingredients ingredients = requireOne(ingredientsDto.getId());
//        BeanUtils.copyProperties(ingredientsDto, ingredients);
//        ingredientsRepository.save(ingredients);
//    }

    public Ingredients getById(Integer id) {
        Ingredients ingredients = requireOne(id);
        return ingredients;
    }


//    private IngredientsDTO toDTO(Ingredients original) {
//        IngredientsDTO ingredientsDto = new IngredientsDTO();
//        BeanUtils.copyProperties(original, ingredientsDto);
//        return ingredientsDto;
//    }

    private Ingredients requireOne(Integer id) {
        return ingredientsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
