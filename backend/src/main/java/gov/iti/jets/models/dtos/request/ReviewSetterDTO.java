package gov.iti.jets.models.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import gov.iti.jets.models.dtos.RecipeDTO;
import gov.iti.jets.models.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewSetterDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;

    private String comment;

    private Date date;

    private Integer recipeId;

    private Integer userId;
}
