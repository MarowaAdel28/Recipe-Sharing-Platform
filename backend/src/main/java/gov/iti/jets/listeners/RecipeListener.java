package gov.iti.jets.listeners;

import gov.iti.jets.models.entities.Recipe;
import gov.iti.jets.util.RecipeStatus;
import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RecipeListener {

    @PrePersist
    public void beforeInsert(Recipe recipe) {
        recipe.setCooksCount(0);
        recipe.setStatus(RecipeStatus.WAITING.toString());
        recipe.setDeleted(false);
        recipe.setDate(new Date());
    }
}
