package gov.iti.jets.models.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import gov.iti.jets.models.dtos.RecipeDTO;
import gov.iti.jets.models.dtos.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class ReviewResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;

    private String comment;

    private Integer rate;

    @JsonFormat(pattern="MMMM dd, yyyy")
    private Date date;

    private RecipeDTO recipeId;

    private UserDTO userId;
}
