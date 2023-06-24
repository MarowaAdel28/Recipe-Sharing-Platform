package gov.iti.jets.controllers;

import gov.iti.jets.models.dtos.RecipeDTO;
import gov.iti.jets.models.dtos.response.RecipeResponseDTO;
import gov.iti.jets.models.dtos.request.RecipeSetterDTO;
import gov.iti.jets.models.dtos.response.RecipeResponseDTO;
import gov.iti.jets.models.dtos.SearchResultDTO;
import gov.iti.jets.models.dtos.request.RecipeSetterDTO;
import gov.iti.jets.models.entities.Category;
import gov.iti.jets.services.RecipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8060")
@Validated
@RestController
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @PostMapping
    public String save(@Valid @RequestBody RecipeSetterDTO recipeDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Invalid Data";
        }
        System.out.println("recipeDto = " + recipeDto);
        return recipeService.save(recipeDto).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        recipeService.delete(id);
    }

//    @PutMapping
//    public void update(@Valid @RequestBody RecipeDTO recipeDto) {
//        recipeService.update(recipeDto);
//    }

    @GetMapping("/{id}")
    public RecipeDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return recipeService.getById(id);
    }
    @GetMapping("/getAll")
    public List<RecipeDTO> getAllRecipes() {
        return recipeService.getAllRecipes();
    }
    @GetMapping("top3")
    public List<RecipeDTO> getTop3CRecipes() {
        return recipeService.getTop3();
    }
    @GetMapping("/getRecipesByPageNo")
    public ResponseEntity<RecipeResponseDTO> getPaginationRecipes(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "9") int size) {
        return recipeService.getPaginatedRecipes(page,size);
    }
    @GetMapping("/findRecipesByNameAndCategory")
    public ResponseEntity<SearchResultDTO> searchRecipesByNameAndCategory(
            @RequestParam(value = "name", required = false) String keyword,
            @RequestParam(value = "categoryId", required = false) Category categoryId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "9") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return recipeService.searchRecipesByName(keyword, categoryId, pageable);
    }

//    @GetMapping("/findRecipesByName")
//    public ResponseEntity<SearchResultDTO> searchRecipesByName(
//            @RequestParam("keyword") String keyword,
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "size", defaultValue = "9") int size
//    )
//    {
//        PageRequest pageable = PageRequest.of(page, size);
//        return recipeService.searchRecipesByName(keyword, pageable);
//    }

}
