package gov.iti.jets.services;

import gov.iti.jets.models.dtos.IngredientsDTO;
import gov.iti.jets.models.entities.Ingredients;
import gov.iti.jets.repositories.IngredientsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class IngredientsService {

    @Autowired
    private IngredientsRepository ingredientsRepository;

    public Integer save(IngredientsDTO ingredientsDto) {
        Ingredients ingredients = new Ingredients();
        BeanUtils.copyProperties(ingredientsDto, ingredients);
        ingredients = ingredientsRepository.save(ingredients);
        return ingredients.getId();
    }

    public void delete(Integer id) {
        ingredientsRepository.deleteById(id);
    }

    public void update( IngredientsDTO ingredientsDto) {
        Ingredients ingredients = requireOne(ingredientsDto.getId());
        BeanUtils.copyProperties(ingredientsDto, ingredients);
        ingredientsRepository.save(ingredients);
    }

    public IngredientsDTO getById(Integer id) {
        Ingredients ingredients = requireOne(id);
        return toDTO(ingredients);
    }


    private IngredientsDTO toDTO(Ingredients original) {
        IngredientsDTO ingredientsDto = new IngredientsDTO();
        BeanUtils.copyProperties(original, ingredientsDto);
        return ingredientsDto;
    }

    private Ingredients requireOne(Integer id) {
        return ingredientsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
