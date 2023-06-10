package gov.iti.jets.models.dtos;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ReviewDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String comment;

    private Integer rate;

    private LocalDateTime date;

    private Integer recipeId;

    private Integer userId;

}
