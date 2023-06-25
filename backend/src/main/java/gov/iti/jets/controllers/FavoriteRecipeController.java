package gov.iti.jets.controllers;

import gov.iti.jets.models.dtos.FavoriteRecipeDTO;
import gov.iti.jets.models.dtos.request.FavouriteSetterDTO;
import gov.iti.jets.models.dtos.response.FavouriteResponseDTO;
import gov.iti.jets.services.FavoriteRecipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@Validated
@RestController
@RequestMapping("/favoriteRecipe")
public class FavoriteRecipeController {

    @Autowired
    private FavoriteRecipeService favoriteRecipeService;

    @PostMapping
    public String save(@Valid @RequestBody FavouriteSetterDTO vO) {
        System.out.println("Martinaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        return favoriteRecipeService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
//        favoriteRecipeService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @RequestBody FavoriteRecipeDTO favoriteRecipeDto) {
        favoriteRecipeService.update(favoriteRecipeDto);
    }

    @GetMapping("/getByIds")
    public FavouriteResponseDTO getById(@Valid @RequestBody FavouriteResponseDTO favoriteRecipeDTO) {
        System.out.println("favoriteRecipeDTO = " + favoriteRecipeDTO);
        return favoriteRecipeService.getById(favoriteRecipeDTO);
    }

    @PostMapping("/deletefav")
    public void deleteFavouriteByRecipeIdAndUserId(@Valid @RequestBody FavouriteResponseDTO favoriteRecipeDTO){
        favoriteRecipeService.delete(favoriteRecipeDTO);
    }

    @GetMapping
    public Optional<FavouriteResponseDTO> getByIds(@RequestParam int userId,
                                                  @RequestParam int recipeId){
        return favoriteRecipeService.getByUserAndRecipeId(userId,recipeId);
    }
//    @GetMapping
//    public Page<FavoriteRecipeDTO> query(@Valid FavoriteRecipeQueryVO vO) {
//        return favoriteRecipeService.query(vO);
//    }
}
