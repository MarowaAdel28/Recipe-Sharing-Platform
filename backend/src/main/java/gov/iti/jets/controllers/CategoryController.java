package gov.iti.jets.controllers;

import gov.iti.jets.models.dtos.CategoryDTO;
import gov.iti.jets.services.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:8060")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAll();
    }

    @GetMapping("top3")
    public List<CategoryDTO> getTop3Categories() {
        return categoryService.getTop3();
    }

    @PostMapping
    public String save(@Valid @RequestBody CategoryDTO categoryDto) {
        return categoryService.save(categoryDto).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        categoryService.delete(id);
    }

    @PutMapping
    public void update(@Valid @RequestBody CategoryDTO categoryDto) {
        categoryService.update(categoryDto);
    }

    @GetMapping("/{id}")
    public CategoryDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return categoryService.getById(id);
    }

//    @GetMapping
//    public Page<CategoryDTO> query(@Valid CategoryQueryVO categoryDto) {
//        return categoryService.query(categoryDto);
//    }
}
