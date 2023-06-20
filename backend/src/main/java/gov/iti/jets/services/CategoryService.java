package gov.iti.jets.services;

import gov.iti.jets.models.dtos.CategoryDTO;
import gov.iti.jets.models.entities.Category;
import gov.iti.jets.repositories.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Integer save(CategoryDTO categoryDto) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto, category);
        category = categoryRepository.save(category);
        return category.getId();
    }

    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

//    public void update(CategoryDTO categoryDto) {
//        Category category = requireOne(categoryDto.getId());
//        BeanUtils.copyProperties(categoryDto, category);
//        categoryRepository.save(category);
//    }

//    public CategoryDTO getById(Integer id) {
//        Category original = requireOne(id);
//        return toDTO(original);
//    }

//    private CategoryDTO toDTO(Category category) {
//        CategoryDTO categoryDto = new CategoryDTO();
//        BeanUtils.copyProperties(category, categoryDto);
//        return categoryDto;
//    }

//    private Category requireOne(Integer id) {
//        return categoryRepository.findById(id)
//                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
//    }

    public Category getReference(Integer id) {
        return categoryRepository.getReferenceById(id);
    }

    public List<CategoryDTO> getAll() {
        List<Category> originals = categoryRepository.findAll();
        List<CategoryDTO> target = new ArrayList<>();
        originals.forEach(category -> {
            target.add(toDTO(category));
        });
        return target;
    }


    public List<CategoryDTO> getTop3() {
        Pageable pageable = PageRequest.of(0, 3); // Limit the results to 3
        List<Category> categories = categoryRepository.findAll(pageable).getContent();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category cat : categories)
            categoryDTOS.add(toDTO(cat));
        return categoryDTOS;
    }

    private CategoryDTO toDTO(Category category) {
        CategoryDTO categoryDto = new CategoryDTO();
        BeanUtils.copyProperties(category, categoryDto);
        return categoryDto;
    }

    private Category requireOne(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

}
