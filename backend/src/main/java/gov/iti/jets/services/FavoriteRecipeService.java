package gov.iti.jets.services;

import gov.iti.jets.models.dtos.FavoriteRecipeDTO;
import gov.iti.jets.models.dtos.request.FavouriteSetterDTO;
import gov.iti.jets.models.dtos.response.FavouriteResponseDTO;
import gov.iti.jets.models.entities.FavoriteRecipe;
import gov.iti.jets.repositories.FavoriteRecipeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FavoriteRecipeService {

    @Autowired
    private FavoriteRecipeRepository favoriteRecipeRepository;

    ModelMapper mapper = new ModelMapper();

    public Integer save(FavouriteSetterDTO favoriteRecipeDto) {
        System.out.println("favoriteRecipeDto = " + favoriteRecipeDto);
        FavoriteRecipe favoriteRecipe = mapper.map(favoriteRecipeDto,FavoriteRecipe.class);
        favoriteRecipe = favoriteRecipeRepository.save(favoriteRecipe);
        return favoriteRecipe.getId();
    }

    public void delete(FavouriteResponseDTO favoriteRecipeDTO) {
        System.out.println("favoriteRecipeDTO from deleted = " + favoriteRecipeDTO);
        FavoriteRecipe favoriteRecipe = mapper.map(favoriteRecipeDTO , FavoriteRecipe.class);
        favoriteRecipeRepository.deleteFavoriteRecipeByRecipeIdAndUserId(favoriteRecipe.getRecipeId(), favoriteRecipe.getUserId());
    }

    public void update(FavoriteRecipeDTO favoriteRecipeDto) {
        FavoriteRecipe favoriteRecipe = requireOne(favoriteRecipeDto.getId());
        BeanUtils.copyProperties(favoriteRecipeDto, favoriteRecipe);
        favoriteRecipeRepository.save(favoriteRecipe);
    }

    public FavouriteResponseDTO getById(FavouriteResponseDTO favoriteRecipeDTO) {
        FavoriteRecipe favoriteRecipe = mapper.map(favoriteRecipeDTO , FavoriteRecipe.class);
        favoriteRecipeRepository.findDistinctByRecipeIdAndUserId(favoriteRecipe.getRecipeId(),favoriteRecipe.getUserId());
        return toDTO(favoriteRecipe);
    }


    private FavouriteResponseDTO toDTO(FavoriteRecipe original) {
        return mapper.map(original,FavouriteResponseDTO.class);
    }

    private FavoriteRecipe requireOne(Integer id) {
        return favoriteRecipeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
