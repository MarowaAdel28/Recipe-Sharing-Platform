package gov.iti.jets.controllers;

import gov.iti.jets.models.dtos.RecipeDTO;
import gov.iti.jets.models.dtos.ReviewDTO;
import gov.iti.jets.models.dtos.request.RecipeSetterDTO;
import gov.iti.jets.models.dtos.request.ReviewSetterDTO;
import gov.iti.jets.models.dtos.response.ReviewResponseDTO;
import gov.iti.jets.services.ReviewService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Validated
@RestController
//@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public String save(@Valid @RequestBody ReviewSetterDTO reviewDto) {
        System.out.println("reviewDto = " + reviewDto);
        return reviewService.save(reviewDto).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        reviewService.delete(id);
    }

    @PutMapping
    public void update(@Valid @RequestBody RecipeSetterDTO recipeSetterDTO, Integer newRate) {
        System.out.println("newRate = " + newRate);
        reviewService.update(recipeSetterDTO , newRate);
    }

    @GetMapping("/{id}")
    public ReviewResponseDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return reviewService.getById(id);
    }

    @GetMapping("/recipe/{recipeId}")
    public List<ReviewResponseDTO> getReviewByRecipeId(@PathVariable("recipeId") Integer recipeId) {

        return reviewService.getReviewsByRecipeId(recipeId);
    }

//    @GetMapping
//    public Page<ReviewDTO> query(@Valid ReviewQueryVO reviewDto) {
//        return reviewService.query(reviewDto);
//    }
}
