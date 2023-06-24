package gov.iti.jets.services;

import gov.iti.jets.models.dtos.RejectedRecipeDTO;
import gov.iti.jets.models.dtos.adminrecipes.AdminRecipeDTO;
import gov.iti.jets.repositories.AdminRecipesRepository;
import gov.iti.jets.util.Utility;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminRecipesService {

    private AdminRecipesRepository adminRecipesRepository;
    private RejectedRecipeService rejectedRecipeService;

    public AdminRecipesService(AdminRecipesRepository adminRecipesRepository, RejectedRecipeService rejectedRecipeService) {
        this.adminRecipesRepository = adminRecipesRepository;
        this.rejectedRecipeService = rejectedRecipeService;
    }

    public List<AdminRecipeDTO> getAcceptedRecipes(PageRequest page) {
        return adminRecipesRepository.findByStatusIgnoreCase(Utility.ACCEPTED_STATUS, page);
    }

    public Long getAcceptedCount() {
        return adminRecipesRepository.countDistinctByStatusIgnoreCase(Utility.ACCEPTED_STATUS);
    }
    public List<AdminRecipeDTO> getWaitingRecipes(PageRequest page) {
        return adminRecipesRepository.findByStatusIgnoreCase(Utility.WAITING_STATUS, page);
    }
    public Long getWaitingCount() {
        return adminRecipesRepository.countDistinctByStatusIgnoreCase(Utility.WAITING_STATUS);
    }

    public List<AdminRecipeDTO> getRejectedRecipes(PageRequest page) {
        return adminRecipesRepository.findByStatusIgnoreCase(Utility.REJECTED_STATUS, page);
    }
    public Long getRejectedCount() {
        return adminRecipesRepository.countDistinctByStatusIgnoreCase(Utility.REJECTED_STATUS);
    }


    public Integer acceptRecipe(Integer recipeId) {
       return adminRecipesRepository.updateStatusById(recipeId, Utility.ACCEPTED_STATUS);
    }

    public Integer rejectRecipe(Integer recipeId, String message) {
        int result = adminRecipesRepository.updateStatusById(recipeId, Utility.REJECTED_STATUS);
        if(result == 1) {
            rejectedRecipeService.save(new RejectedRecipeDTO(recipeId, message));
        }
        return result;
    }
}
