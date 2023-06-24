package gov.iti.jets.services;

import gov.iti.jets.models.dtos.FavoriteRecipeDTO;
import gov.iti.jets.models.entities.FavoriteRecipe;
import gov.iti.jets.repositories.FavoriteRecipeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FavoriteRecipeService {

    @Autowired
    private FavoriteRecipeRepository favoriteRecipeRepository;

    public Integer save(FavoriteRecipeDTO favoriteRecipeDto) {
        FavoriteRecipe bean = new FavoriteRecipe();
        BeanUtils.copyProperties(favoriteRecipeDto, bean);
        bean = favoriteRecipeRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        favoriteRecipeRepository.deleteById(id);
    }

    public void update(FavoriteRecipeDTO favoriteRecipeDto) {
        FavoriteRecipe favoriteRecipe = requireOne(favoriteRecipeDto.getId());
        BeanUtils.copyProperties(favoriteRecipeDto, favoriteRecipe);
        favoriteRecipeRepository.save(favoriteRecipe);
    }

    public FavoriteRecipeDTO getById(Integer id) {
        FavoriteRecipe original = requireOne(id);
        return toDTO(original);
    }


    private FavoriteRecipeDTO toDTO(FavoriteRecipe original) {
        FavoriteRecipeDTO bean = new FavoriteRecipeDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private FavoriteRecipe requireOne(Integer id) {
        return favoriteRecipeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
