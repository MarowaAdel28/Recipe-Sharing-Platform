package gov.iti.jets.controllers;

import gov.iti.jets.models.dtos.recipeposter.RecipeSetterDTO;
import gov.iti.jets.services.RecipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
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

//    @GetMapping("/{id}")
//    public RecipeDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
//        return recipeService.getById(id);
//    }

//    @GetMapping
//    public Page<RecipeDTO> query(@Valid RecipeQueryVO recipeDto) {
//        return recipeService.query(recipeDto);
//    }
}
