package gov.iti.jets.services;

import gov.iti.jets.models.dtos.RecipeDTO;
import gov.iti.jets.models.dtos.ReviewDTO;
import gov.iti.jets.models.dtos.request.RecipeSetterDTO;
import gov.iti.jets.models.dtos.request.ReviewSetterDTO;
import gov.iti.jets.models.dtos.response.ReviewResponseDTO;
import gov.iti.jets.models.entities.Recipe;
import gov.iti.jets.models.entities.Review;
import gov.iti.jets.repositories.RecipeRepository;
import gov.iti.jets.repositories.ReviewRepository;
import gov.iti.jets.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
            private RecipeRepository recipeRepository;

    ModelMapper mapper = new ModelMapper();
    public Integer save(ReviewSetterDTO reviewDto) {
        System.out.println("reviewDto = " + reviewDto);
        Review review = mapper.map(reviewDto,Review.class);
        review = reviewRepository.save(review);
        return review.getId();
    }

    public void delete(Integer id) {
        reviewRepository.deleteById(id);
    }

    public void update(ReviewDTO reviewDto) {
        Review review = requireOne(reviewDto.getId());
        BeanUtils.copyProperties(reviewDto, review);
        reviewRepository.save(review);
    }

    public List<ReviewResponseDTO> getReviewsByRecipeId(Integer recipeDto){
        Recipe recipe = recipeRepository.findById(recipeDto).get();
        List<Review> recipes = reviewRepository.retreiveReviewsByRecipeId(recipe);
        return recipes.stream()
                .map((element) -> mapper.map(element, ReviewResponseDTO.class))
                .collect(Collectors.toList());
    }

    public ReviewResponseDTO getById(Integer id) {
        Review review = requireOne(id);
        return toDTO(review);
    }

//    public Page<ReviewDTO> query(ReviewQueryVO reviewDto) {
//        throw new UnsupportedOperationException();
//    }

    private ReviewResponseDTO toDTO(Review review) {

        return mapper.map(review, ReviewResponseDTO.class);
    }

    private Review requireOne(Integer id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
