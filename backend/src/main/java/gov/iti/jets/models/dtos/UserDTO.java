package gov.iti.jets.models.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;

    private String email;

    private String password;

//    @JsonProperty("createDate")
    //private Date createTime;

    private Integer id;

    private Integer age;

    private Character gender;

    //private Boolean admin;

    //private Boolean deleted;
    
//    private List<FavoriteRecipeDTO> favoriteRecipeList;
//
//    private List<ReviewDTO> reviewList;
//
//    private List<RecipeDTO> recipeList;

}
