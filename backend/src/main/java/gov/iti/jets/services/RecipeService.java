package gov.iti.jets.services;

import gov.iti.jets.models.dtos.IngredientsDTO;
import gov.iti.jets.models.dtos.RecipeDTO;
import gov.iti.jets.models.dtos.response.RecipeResponseDTO;
import gov.iti.jets.models.dtos.SearchResultDTO;
import gov.iti.jets.models.dtos.RecipeHasIngredientsDTO;
import gov.iti.jets.models.dtos.profile.UserRecipeDTO;
import gov.iti.jets.models.dtos.profile.UserRecipeResponseDTO;
import gov.iti.jets.models.entities.Recipe;
import gov.iti.jets.repositories.FavoriteRecipeRepository;
import gov.iti.jets.repositories.RecipeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import gov.iti.jets.mappers.RecipeMapper;
import gov.iti.jets.models.dtos.request.RecipeIngredientsDTO;
import gov.iti.jets.models.dtos.request.RecipeSetterDTO;
import gov.iti.jets.models.entities.*;
import gov.iti.jets.util.Utility;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private CategoryService categoryService;

    @Autowired
            private FavoriteRecipeRepository favoriteRecipeRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private UserService userService;

    @Autowired
    private RecipeMapper recipeMapper;

    @Autowired
    private IngredientsService ingredientsService;

    @Autowired
    private RecipeHasIngredientsService recipeHasIngredientsService;

    public Integer save(RecipeSetterDTO recipeDto) {
//        System.out.println("recipeDto.getCategoryId() = " + recipeDto.getCategoryId());
        Category category = categoryService.getReference(recipeDto.getCategoryId());
        User user = userService.getReference(recipeDto.getUserId());
        if (user == null) {
            return Utility.INVALID_USER;
        }
        if (category == null) {
            return Utility.INVALID_CATEGORY;
        }
        StringBuilder stepBuilder = new StringBuilder();
        recipeDto.getSteps().forEach(step->{
            stepBuilder.append(step);
            stepBuilder.append('/');
        });
        Recipe recipe = recipeMapper.toEntity(recipeDto);
        recipe.setSteps(stepBuilder.toString());
        recipe.setUserId(user);
        recipe.setCategoryId(category);
        recipe = recipeRepository.save(recipe);

        List<RecipeIngredientsDTO> ingredients = ingredientsService.addListOfIngredients(recipeDto.getIngredients());
        recipeHasIngredientsService.addListOfRecipeIngredients(ingredients, recipe.getId());
        return recipe.getId();
    }

//    public LocalDateTime now(){
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formattedDate = now.format(formatter);
//        return formattedDate;
//    }

    public void delete(Integer id) {
        recipeRepository.deleteById(id);
    }
    public void delete (Integer id,boolean isDeleted){
        recipeRepository.updateRecipeDeletion(isDeleted, id);
    }


    public void update(RecipeDTO recipeDto) {
        Recipe bean = requireOne(recipeDto.getId());
        recipeRepository.save(bean);
    }

    public RecipeDTO getById(Integer id) {
        Recipe original = requireOne(id);
//        System.out.println("original = " + original);
        RecipeDTO recipeDTO = toDTO(original);
//        System.out.println("toDTO(original) " + recipeDTO);
        recipeDTO.setRecipeHasIngredientsList(getRecipeHasIngredientList(original));
//        recipeDTO.getRecipeHasIngredientsList().stream().map(r->RecipeHasIngredients::getIngredientList).toList();
        return recipeDTO;
    }

    private List<RecipeHasIngredientsDTO> getRecipeHasIngredientList(Recipe recipe) {
        List<RecipeHasIngredientsDTO> recipeHasIngredientsDTOS = new ArrayList<>();
        List<RecipeHasIngredients> recipeHasIngredientsList = recipe.getRecipeHasIngredientsList();
        recipeHasIngredientsList.forEach(recipeHasIngredients-> {
            RecipeHasIngredientsDTO recipeIngredientsDTO = modelMapper.map(recipeHasIngredients,RecipeHasIngredientsDTO.class);
            Ingredients ingredients = recipeHasIngredients.getIngredients();
            IngredientsDTO ingredientsDTO = modelMapper.map(ingredients, IngredientsDTO.class);
            ingredientsDTO.setName(ingredients.getName());
            recipeIngredientsDTO.setIngredientsId(ingredientsDTO);
            recipeHasIngredientsDTOS.add(recipeIngredientsDTO);
        });
        return recipeHasIngredientsDTOS;
    }
    private RecipeDTO toDTO(Recipe recipe) {
        return modelMapper.map(recipe,RecipeDTO.class);
    }

    private Recipe toEntity(RecipeDTO recipeDto) {
        return modelMapper.map(recipeDto,Recipe.class);
    }

    private Recipe requireOne(Integer id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<RecipeDTO> getTop3() {
        Pageable pageable = PageRequest.of(0, 3); // Limit the results to 3
        List<Recipe> categories = recipeRepository.findAll(pageable).getContent();
        List<RecipeDTO> recipeDTOS = new ArrayList<>();
        for (Recipe recipe : categories)
            recipeDTOS.add(toDTO(recipe));
        return recipeDTOS;
    }

    public List<RecipeDTO> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //    public List<RecipeDTO> getPaginatedRecipes(int page, int pageSize) {
//        Pageable pageable = PageRequest.of(page, pageSize);
//        return recipeRepository.findAll(pageable).stream()
//                .map(this::toDTO)
//                .collect(Collectors.toList());
//    }
    public ResponseEntity<RecipeResponseDTO> getPaginatedRecipes(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        RecipeResponseDTO recipeResponseDTO = new RecipeResponseDTO();
        recipeResponseDTO.setData(recipeRepository.findAll(pageable).stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
        recipeResponseDTO.setTotalItems(getRecipesCount());
        return ResponseEntity.ok(recipeResponseDTO);
    }
    public ResponseEntity<UserRecipeResponseDTO> getAllRecipesForUser(int userId, int page, int pageSize) {
        User user = userService.getReference(userId);
        Pageable pageable = PageRequest.of(page, pageSize);
        UserRecipeResponseDTO recipeResponseDTO = new UserRecipeResponseDTO();
        recipeResponseDTO.setData(recipeRepository.findAllByUserIdAndIsDeletedFalse(user, pageable).stream()
                .map(this::toUserRecipeDTO)
                .collect(Collectors.toList()));
        recipeResponseDTO.setTotalItems(getRecipesCountForUser(userId));
        return ResponseEntity.ok(recipeResponseDTO);
    }

    public ResponseEntity<UserRecipeResponseDTO> getAllArchiveRecipesForUser(int userId, int page, int pageSize) {
        User user = userService.getReference(userId);
        Pageable pageable = PageRequest.of(page, pageSize);
        UserRecipeResponseDTO recipeResponseDTO = new UserRecipeResponseDTO();
        recipeResponseDTO.setData(recipeRepository.findAllByUserIdAndIsDeletedTrue(user, pageable).stream()
                .map(this::toUserRecipeDTO)
                .collect(Collectors.toList()));
        recipeResponseDTO.setTotalItems(getArchiveRecipesCountForUser(userId));
        return ResponseEntity.ok(recipeResponseDTO);
    }

    public ResponseEntity<UserRecipeResponseDTO> getAllFavoriteRecipesForUser(int userId, int page, int pageSize) {
        User user = userService.getReference(userId);
        Pageable pageable = PageRequest.of(page, pageSize);
        UserRecipeResponseDTO recipeResponseDTO = new UserRecipeResponseDTO();
        recipeResponseDTO.setData(favoriteRecipeRepository.findRecipeIdByUserIdAndRecipeIdIsDeletedFalse(user, pageable).stream()
                        .map((recipe)->recipe.getRecipeId())
                .map(this::toUserRecipeDTO)
                .collect(Collectors.toList()));
        recipeResponseDTO.setTotalItems(getFavoriteRecipesCountForUser(userId));
        return ResponseEntity.ok(recipeResponseDTO);
    }

    private UserRecipeDTO toUserRecipeDTO(Recipe recipe) {
        return modelMapper.map(recipe,UserRecipeDTO.class);
    }

    private long getRecipesCountForUser(int userId) {
        User user = userService.getReference(userId);
        return recipeRepository.findAllByUserIdAndIsDeletedFalse(user).size();
    }

    private long getArchiveRecipesCountForUser(int userId) {
        User user = userService.getReference(userId);
        return recipeRepository.findAllByUserIdAndIsDeletedTrue(user).size();
    }

    private long getFavoriteRecipesCountForUser(int userId) {
        User user = userService.getReference(userId);
        return favoriteRecipeRepository.findRecipeIdByUserIdAndRecipeIdIsDeletedFalse(user).size();
    }

    private int getRecipesCount(){
        return (int)recipeRepository.count();
    }
    public ResponseEntity<SearchResultDTO> searchRecipesByName(String name, Category categoryId, Pageable pageable) {
        List<Recipe> recipeList = recipeRepository.searchByNameAndCategoryIgnoreCase(name, categoryId);
        int totalSize = recipeList.size();
        List<Recipe> paginatedList = getPaginatedList(recipeList, pageable);
        List<RecipeDTO> recipeDTOList = paginatedList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        SearchResultDTO searchResultDTO = new SearchResultDTO(recipeDTOList, totalSize);
        return ResponseEntity.ok(searchResultDTO);
    }

    private List<Recipe> getPaginatedList(List<Recipe> recipeList, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), recipeList.size());
        return recipeList.subList(start, end);
    }

//    public ResponseEntity<SearchResultDTO> searchRecipesByName(String keyword, Pageable pageable) {
//        Page<Recipe> recipePage = recipeRepository.searchByNameContainingIgnoreCase(keyword, pageable);
//        List<RecipeDTO> recipeList = recipePage.getContent().stream()
//                .map(this::toDTO)
//                .collect(Collectors.toList());
//        long totalSize = recipePage.getTotalElements();
//        SearchResultDTO searchResultDTO = new SearchResultDTO(recipeList, totalSize);
//        return ResponseEntity.ok(searchResultDTO);
//    }

//    public Page<RecipeDTO> searchRecipesByName(String keyword, Pageable pageable) {
//        return recipeRepository.searchByNameContainingIgnoreCase(keyword, pageable)
//                .map(this::toDTO);
//    }
//    public List<RecipeDTO> searchRecipesByName(String keyword) {
//        List<Recipe> recipes = recipeRepository.findByNameContainingIgnoreCase(keyword);
//        return recipes.stream()
//                .map(this::toDTO)
//                .collect(Collectors.toList());
//    }
}
