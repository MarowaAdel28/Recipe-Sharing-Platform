package gov.iti.jets.controllers;

import gov.iti.jets.models.dtos.adminrecipes.AdminRecipeDTO;
import gov.iti.jets.services.AdminRecipesService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminRecipe")
//@CrossOrigin(origins = "http://localhost:4200")
public class AdminRecipeController {

    private AdminRecipesService adminRecipesService;

    public AdminRecipeController(AdminRecipesService adminRecipesService) {
        this.adminRecipesService = adminRecipesService;
    }

    @GetMapping("/getAcceptedRecipes/{page}")
    public List<AdminRecipeDTO> getAcceptedRecipes(@PathVariable int page) {
        return adminRecipesService.getAcceptedRecipes(PageRequest.of(page, 10));
    }

    @GetMapping("/getAcceptedCount")
    public Long getAcceptedCount() {
        return adminRecipesService.getAcceptedCount();
    }

    @GetMapping("/getRejectedRecipes/{page}")
    public List<AdminRecipeDTO> getRejectedRecipes(@PathVariable int page) {
//        System.out.println("page = " + page);
        List<AdminRecipeDTO> adminRecipeDTOList = adminRecipesService.getRejectedRecipes(PageRequest.of(page, 10));
        System.out.println("adminRecipeDTOList = " + adminRecipeDTOList);
        return adminRecipeDTOList;
    }

    @GetMapping("/getRejectedCount")
    public Long getRejectedCount() {
        return adminRecipesService.getRejectedCount();
    }


    @GetMapping("/getWaitingRecipes/{page}")
    public List<AdminRecipeDTO> getWaitingRecipes(@PathVariable int page) {
        return adminRecipesService.getWaitingRecipes(PageRequest.of(page, 10));
    }

    @GetMapping("/getWaitingCount")
    public Long getWaitingCount() {
        return adminRecipesService.getWaitingCount();
    }

    @PatchMapping("/acceptRecipe/{recipeId}")
    public Integer acceptRecipe(@PathVariable Integer recipeId) {
        return adminRecipesService.acceptRecipe(recipeId);
    }

    @PostMapping("/rejectRecipe")
    public Integer rejectRecipe(@RequestParam("recipeId")Integer recipeId, @RequestParam("message")String message) {
        System.out.println("recipeId = " + recipeId);
        return adminRecipesService.rejectRecipe(recipeId, message);
    }
    
}
