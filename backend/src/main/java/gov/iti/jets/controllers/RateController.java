package gov.iti.jets.controllers;

import gov.iti.jets.models.dtos.RateDto;
import gov.iti.jets.services.RateService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@Validated
@RestController
@RequestMapping("/rate")
public class RateController {

    @Autowired
    RateService rateService;

    @PostMapping
    public void post(@RequestParam int userId,
                     @RequestParam int recipeId,
                     @RequestParam(defaultValue = "0") int rating) {
        System.out.println("rate controller post");
        System.out.println("userId = " + userId);
        System.out.println("recipeId = " + recipeId);
        System.out.println("rating = " + rating);
        rateService.addRate(userId,recipeId,rating);
    }

    @GetMapping
    public RateDto get(@RequestParam int userId,
                       @RequestParam int recipeId) {
        return rateService.getUserRecipeRate(recipeId,userId);
    }

    @GetMapping("/{recipeId}")
    public List<RateDto> getRecipeRate(@Valid @NotNull @PathVariable("recipeId") int recipeId) {
        return rateService.getRecipeRates(recipeId);
    }
}
