package gov.iti.jets.services;

import gov.iti.jets.models.dtos.RateDto;
import gov.iti.jets.models.entities.Rate;
import gov.iti.jets.models.entities.RatePK;
import gov.iti.jets.repositories.RateRepository;
import gov.iti.jets.repositories.RecipeRepository;
import gov.iti.jets.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service

public class RateService {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    ModelMapper mapper = new ModelMapper();

    public void addRate(Integer userId, Integer recipeId, int rating) {
        System.out.println("rating service add");
        RatePK ratePK = new RatePK(userId,recipeId);
        Rate rate = new Rate(ratePK,rating);
        rate.setRecipe(recipeRepository.getReferenceById(recipeId));
        rate.setUser(userRepository.getReferenceById(userId));
        rateRepository.save(rate);
    }

    public RateDto getUserRecipeRate(Integer recipeId, Integer userId){
        RatePK ratePK = new RatePK(userId,recipeId);
        Rate rate = rateRepository.findById(ratePK).get();
        return mapper.map(rate,RateDto.class);
    }

    public List<RateDto> getRecipeRates(Integer recipeId) {
        return rateRepository.findByRecipeId(recipeId).stream().map((rate)->toDto(rate)).toList();
    }

    private RateDto toDto (Rate rate) {
        return mapper.map(rate,RateDto.class);
    }
}
