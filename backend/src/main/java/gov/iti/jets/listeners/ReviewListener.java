package gov.iti.jets.listeners;

import gov.iti.jets.models.entities.Recipe;
import gov.iti.jets.models.entities.Review;
import gov.iti.jets.models.entities.User;
import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ReviewListener {

    @PrePersist
    public void beforeInsert(Review review) {
        review.setDate(new Date());
        }
}
