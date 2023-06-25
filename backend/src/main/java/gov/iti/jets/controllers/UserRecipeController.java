package gov.iti.jets.controllers;

//import gov.iti.jets.models.dtos.RecipeResponseDTO;
import gov.iti.jets.models.dtos.profile.UserRecipeResponseDTO;
import gov.iti.jets.services.RecipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:4200")
@Validated
@RestController
@RequestMapping("/userRecipe")
public class UserRecipeController {
    @Autowired
    private RecipeService RecipeService;

    @GetMapping("/{id}")
    public ResponseEntity<UserRecipeResponseDTO> getPaginationRecipes(@Valid @NotNull @PathVariable("id") Integer id,
                                                                      @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "9") int size) {
        return RecipeService.getAllRecipesForUser(id,page,size);
    }

    @GetMapping("/archive/{id}")
    public ResponseEntity<UserRecipeResponseDTO> getPaginationArchiveRecipes(@Valid @NotNull @PathVariable("id") Integer id,
                                                                      @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "9") int size) {
        return RecipeService.getAllArchiveRecipesForUser(id,page,size);
    }

    @GetMapping("/favorite/{id}")
    public ResponseEntity<UserRecipeResponseDTO> getPaginationFavoriteRecipes(@Valid @NotNull @PathVariable("id") Integer id,
                                                                             @RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "9") int size) {
        return RecipeService.getAllFavoriteRecipesForUser(id,page,size);
    }
}
