package gov.iti.jets.models.dtos;


import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;

    private String email;

    private String password;

    private LocalDateTime createTime;

    private Integer id;

    private Integer age;

    private String gender;

    private Boolean admin;

    private Boolean deleted;
    
    private List<FavoriteRecipeDTO> favoriteRecipeList;
    
    private List<ReviewDTO> reviewList;
    
//    private List<RecipeDTO> recipeList;

    @Override
    public String toString() {
        return "gov.iti.jets.models.entities.User[ id=" + id + " ]";
    }


}
