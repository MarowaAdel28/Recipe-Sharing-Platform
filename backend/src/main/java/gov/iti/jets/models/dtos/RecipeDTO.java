package gov.iti.jets.models.dtos;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class RecipeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer cooksCount;

    private String steps;

    private String status;

    private LocalDateTime date;

    private Integer userId;

    private Integer categoryId;

    private Boolean deleted;

    private String preparingTime;

    private Integer persons;

}
