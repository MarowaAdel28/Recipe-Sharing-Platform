package gov.iti.jets.models.dtos.stats;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


public interface UserStatDTO extends Serializable {
    Integer getId();
    String getUserName();
    String getEmail();
    @JsonFormat(pattern="dd/MM/yyyy hh:mm")
    LocalDateTime getCreateTime();
    Integer getAge();
    String getGender();
    List<RecipeDTO> getRecipeList();
    interface RecipeDTO{
        String getRecipeName();
        Integer getId();
    }

}
