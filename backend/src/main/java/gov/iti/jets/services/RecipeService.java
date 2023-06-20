package gov.iti.jets.services;

import gov.iti.jets.models.dtos.RecipeDTO;
import gov.iti.jets.models.entities.Recipe;
import gov.iti.jets.repositories.RecipeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    ModelMapper modelMapper = new ModelMapper();

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

    public List<RecipeDTO> getAll(){
        return modelMapper.map(recipeRepository.findAll(),
                new TypeToken<List<RecipeDTO>>(){}.getType());
    }
}
