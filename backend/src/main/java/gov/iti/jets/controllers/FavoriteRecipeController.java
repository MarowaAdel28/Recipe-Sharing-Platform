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

@Validated
@RestController
@RequestMapping("/favoriteRecipe")
@CrossOrigin(origins = "http://localhost:8080")
public class FavoriteRecipeController {

    @Autowired
    private FavoriteRecipeService favoriteRecipeService;

    @PostMapping
    public String save(@Valid @RequestBody FavouriteSetterDTO vO) {
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

    @GetMapping("/{id}")
    public FavoriteRecipeDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return favoriteRecipeService.getById(id);
    }

    @PostMapping("/deletefav")
    public void deleteFavouriteByRecipeIdAndUserId(@Valid @RequestBody FavouriteResponseDTO favoriteRecipeDTO){
        favoriteRecipeService.delete(favoriteRecipeDTO);
    }
//    @GetMapping
//    public Page<FavoriteRecipeDTO> query(@Valid FavoriteRecipeQueryVO vO) {
//        return favoriteRecipeService.query(vO);
//    }
}
