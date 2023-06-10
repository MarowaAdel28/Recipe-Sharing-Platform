package gov.iti.jets.services;

import gov.iti.jets.models.dtos.ReviewDTO;
import gov.iti.jets.models.entities.Review;
import gov.iti.jets.repositories.ReviewRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Integer save(ReviewDTO reviewDto) {
        Review review = new Review();
        BeanUtils.copyProperties(reviewDto, review);
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

    public ReviewDTO getById(Integer id) {
        Review review = requireOne(id);
        return toDTO(review);
    }

//    public Page<ReviewDTO> query(ReviewQueryVO reviewDto) {
//        throw new UnsupportedOperationException();
//    }

    private ReviewDTO toDTO(Review review) {
        ReviewDTO reviewDto = new ReviewDTO();
        BeanUtils.copyProperties(review, reviewDto);
        return reviewDto;
    }

    private Review requireOne(Integer id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
