package gov.iti.jets.services;

import gov.iti.jets.models.dtos.stats.*;
import gov.iti.jets.repositories.RecipeRepository;
import gov.iti.jets.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {

    private static UserRepository userRepository;
    private RecipeRepository recipeRepository;

    @Autowired
    public StatisticsService(UserRepository userRepository, RecipeRepository recipeRepository){
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }

    public StatisticsService() {}
    public GenderStatDTO getGenderStat() {
        return userRepository.findGenderCount();
    }

    public AgeStatDTO getAgeStat() {
        return userRepository.findAgeCount();
    }

    public RegistrationDateStatDTO getRegisterDateStat() {
        return userRepository.findRegisterDateCount();
    }

    public RecipeStatusStatDTO getRecipeStatusStat() {
        return recipeRepository.findStatusCount();
    }

    public  List<UserStatDTO> getUsersStat(PageRequest pageRequest) {
        return userRepository.getUsers(pageRequest);
    }

    public Long getUsersCount() {
        return userRepository.count();
    }
}
