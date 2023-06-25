package gov.iti.jets.controllers;

import gov.iti.jets.models.dtos.ImageDTO;
import gov.iti.jets.services.ImageService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Validated
@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:8080")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<String> save(@RequestParam("image") MultipartFile image, @Valid @RequestParam int recipeId) {
        return imageService.uploadImage(image,recipeId);
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        imageService.delete(id);
    }

    @PutMapping
    public void update(@Valid @RequestBody ImageDTO imageDTO) {
        imageService.update(imageDTO);
    }

    @GetMapping("/{id}")
    public ImageDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return imageService.getById(id);
    }

//    @GetMapping
//    public Page<ImageDTO> query(@Valid ImageDTO imageDTO) {
//        return imageService.query(imageDTO);
//    }
}
