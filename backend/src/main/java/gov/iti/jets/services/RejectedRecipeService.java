package gov.iti.jets.services;

import gov.iti.jets.models.dtos.RejectedRecipeDTO;
import gov.iti.jets.models.entities.Recipe;
import gov.iti.jets.models.entities.RejectedRecipe;
import gov.iti.jets.repositories.RecipeRepository;
import gov.iti.jets.repositories.RejectedRecipeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RejectedRecipeService {

    private RejectedRecipeRepository rejectedRecipeRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    public RejectedRecipeService(RejectedRecipeRepository rejectedRecipeRepository){
        this.rejectedRecipeRepository = rejectedRecipeRepository;
    }

    public Integer save(RejectedRecipeDTO rejectedRecipeDto) {
        System.out.println("rejectedRecipeDto = " + rejectedRecipeDto);
        RejectedRecipe bean = new RejectedRecipe();
        bean.setMessage(rejectedRecipeDto.getMessage());
        Recipe recipe = recipeRepository.getReferenceById(rejectedRecipeDto.getId());
        bean.setRecipe(recipe);
        System.out.println("recipe.getId() = " + recipe.getId());
        System.out.println(bean.getRecipe().getId());
        bean = rejectedRecipeRepository.save(bean);
        System.out.println(bean);
        System.out.println("recipe.getId() = " + bean.getRecipe().getId());
        return bean.getId();
    }

//    public void delete(Integer id) {
//        rejectedRecipeRepository.deleteById(id);
//    }
//
//    public void update(RejectedRecipeDTO rejectedRecipeDto) {
//        RejectedRecipe rejectedRecipe = requireOne(rejectedRecipeDto.getId());
//        BeanUtils.copyProperties(rejectedRecipeDto, rejectedRecipe);
//        rejectedRecipeRepository.save(rejectedRecipe);
//    }
//
//    public RejectedRecipeDTO getById(Integer id) {
//        RejectedRecipe original = requireOne(id);
//        return toDTO(original);
//    }
//
////    public Page<RejectedRecipeDTO> query(RejectedRecipeQueryVO rejectedRecipeDto) {
////        throw new UnsupportedOperationException();
////    }
//
//    private RejectedRecipeDTO toDTO(RejectedRecipe rejectedRecipe) {
//        RejectedRecipeDTO rejectedRecipeDto = new RejectedRecipeDTO();
//        BeanUtils.copyProperties(rejectedRecipe, rejectedRecipeDto);
//        return rejectedRecipeDto;
//    }
//
//    private RejectedRecipe requireOne(Integer id) {
//        return rejectedRecipeRepository.findById(id)
//                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
//    }
}
