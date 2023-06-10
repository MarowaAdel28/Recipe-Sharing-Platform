package gov.iti.jets.services;

import gov.iti.jets.models.dtos.RejectedRecipeDTO;
import gov.iti.jets.models.entities.RejectedRecipe;
import gov.iti.jets.repositories.RejectedRecipeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RejectedRecipeService {

    @Autowired
    private RejectedRecipeRepository rejectedRecipeRepository;

    public Integer save(RejectedRecipeDTO rejectedRecipeDto) {
        RejectedRecipe bean = new RejectedRecipe();
        BeanUtils.copyProperties(rejectedRecipeDto, bean);
        bean = rejectedRecipeRepository.save(bean);
        return bean.getRecipeId();
    }

    public void delete(Integer id) {
        rejectedRecipeRepository.deleteById(id);
    }

    public void update(RejectedRecipeDTO rejectedRecipeDto) {
        RejectedRecipe rejectedRecipe = requireOne(rejectedRecipeDto.getRecipeId());
        BeanUtils.copyProperties(rejectedRecipeDto, rejectedRecipe);
        rejectedRecipeRepository.save(rejectedRecipe);
    }

    public RejectedRecipeDTO getById(Integer id) {
        RejectedRecipe original = requireOne(id);
        return toDTO(original);
    }

//    public Page<RejectedRecipeDTO> query(RejectedRecipeQueryVO rejectedRecipeDto) {
//        throw new UnsupportedOperationException();
//    }

    private RejectedRecipeDTO toDTO(RejectedRecipe rejectedRecipe) {
        RejectedRecipeDTO rejectedRecipeDto = new RejectedRecipeDTO();
        BeanUtils.copyProperties(rejectedRecipe, rejectedRecipeDto);
        return rejectedRecipeDto;
    }

    private RejectedRecipe requireOne(Integer id) {
        return rejectedRecipeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
