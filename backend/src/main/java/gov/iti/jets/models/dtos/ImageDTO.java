package gov.iti.jets.models.dtos;


import lombok.Data;

import java.io.Serializable;

@Data
public class ImageDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String path;

    private Integer recipeId;

}
