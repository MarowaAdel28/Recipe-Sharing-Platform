package gov.iti.jets.services;

import gov.iti.jets.models.dtos.ImageDTO;
import gov.iti.jets.models.entities.Image;
import gov.iti.jets.repositories.ImageRepository;
import gov.iti.jets.repositories.RecipeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private RecipeRepository recipeRepository;
        public ResponseEntity<String> uploadImage(MultipartFile image,int recipeId) {
            if (image.isEmpty()) {
                return new ResponseEntity<>("Image file is required.", HttpStatus.BAD_REQUEST);
            }

            try {
                // Define the directory to save the uploaded image
                String uploadDir = "../../../../../../../../frontend/recipe-app/src/assets/upload";

                // Create the directory if it doesn't exist
                File directory = new File(uploadDir);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                // Save the image file
                String fileName = "recipe_"+recipeId;
                File file = new File(uploadDir + fileName);
                image.transferTo(file);
                save(recipeId);
                return new ResponseEntity<>("Image uploaded successfully.", HttpStatus.OK);
            } catch (IOException e) {
                return new ResponseEntity<>("Failed to upload image.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    public Integer save(int recipeId) {
        Image image = new Image();
        image.setRecipeId(recipeRepository.getReferenceById(recipeId));
        image.setPath("recipe_"+recipeId);
        image = imageRepository.save(image);
        return image.getId();
    }

    public void delete(Integer id) {
        imageRepository.deleteById(id);
    }

    public void update(ImageDTO imageDto) {
        Image image = requireOne(imageDto.getId());
        BeanUtils.copyProperties(imageDto, image);
        imageRepository.save(image);
    }

    public ImageDTO getById(Integer id) {
        Image original = requireOne(id);
        return toDTO(original);
    }


    private ImageDTO toDTO(Image original) {
        ImageDTO bean = new ImageDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Image requireOne(Integer id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
