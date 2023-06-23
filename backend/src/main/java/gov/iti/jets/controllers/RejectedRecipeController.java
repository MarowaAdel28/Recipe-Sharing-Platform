package gov.iti.jets.controllers;

import gov.iti.jets.models.dtos.RejectedRecipeDTO;
import gov.iti.jets.services.RejectedRecipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/rejectedRecipe")
@CrossOrigin(origins = "http://localhost:8060")
public class RejectedRecipeController {

    @Autowired
    private RejectedRecipeService rejectedRecipeService;

    @PostMapping
    public String save(@Valid @RequestBody RejectedRecipeDTO rejectedRecipeDto) {
        return rejectedRecipeService.save(rejectedRecipeDto).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        rejectedRecipeService.delete(id);
    }
    

    @GetMapping("/{id}")
    public RejectedRecipeDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return rejectedRecipeService.getById(id);
    }

//    @GetMapping
//    public Page<RejectedRecipeDTO> query(@Valid RejectedRecipeQueryVO rejectedRecipeDto) {
//        return rejectedRecipeService.query(rejectedRecipeDto);
//    }
}
