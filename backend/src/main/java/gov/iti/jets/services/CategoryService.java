package gov.iti.jets.services;

import gov.iti.jets.models.dtos.CategoryDTO;
import gov.iti.jets.models.entities.Category;
import gov.iti.jets.repositories.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    public void update(CategoryDTO categoryDto) {
        Category category = requireOne(categoryDto.getId());
        BeanUtils.copyProperties(categoryDto, category);
        categoryRepository.save(category);
    }

    public CategoryDTO getById(Integer id) {
        Category original = requireOne(id);
        return toDTO(original);
    }

    public List<CategoryDTO> getAll() {
        List<Category> originals = categoryRepository.findAll();
        List<CategoryDTO> target = new ArrayList<>();
        originals.forEach(category -> {
            target.add(toDTO(category));
        });
        return target;
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
