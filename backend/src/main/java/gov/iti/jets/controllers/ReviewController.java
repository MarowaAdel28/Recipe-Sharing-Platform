package gov.iti.jets.controllers;

import gov.iti.jets.models.dtos.ReviewDTO;
import gov.iti.jets.services.ReviewService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Validated
@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public String save(@Valid @RequestBody ReviewDTO reviewDto) {
        return reviewService.save(reviewDto).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        reviewService.delete(id);
    }

    @PutMapping
    public void update(@Valid @RequestBody ReviewDTO reviewDto) {
        reviewService.update(reviewDto);
    }

    @GetMapping("/{id}")
    public ReviewDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return reviewService.getById(id);
    }

//    @GetMapping
//    public Page<ReviewDTO> query(@Valid ReviewQueryVO reviewDto) {
//        return reviewService.query(reviewDto);
//    }
}
