package gov.iti.jets.controllers;

import gov.iti.jets.models.dtos.ImageDTO;
import gov.iti.jets.services.ImageService;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Validated
@MultipartConfig
@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:8080")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/upload/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, @PathVariable("id") int id) {
        if (!file.isEmpty()) {

            try {
                String fileName="../../../../../../../../frontend/recipe-app/src/assets/upload/recipe_"+id;
                Path targetPath = Paths.get(fileName);
                    Files.copy(file.getInputStream(),targetPath);

//                byte[] bytes = file.getBytes();
//                FileOutputStream out = new FileOutputStream("../../../../../../../../frontend/recipe-app/src/assets/upload/recipe_"+id);
//                        out.write(bytes);
                        imageService.save(id);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "You successfully uploaded file=" + file.getOriginalFilename();
        } else {
            return "You failed to upload " + file.getOriginalFilename()
                    + " because the file was empty.";
        }

    }

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
